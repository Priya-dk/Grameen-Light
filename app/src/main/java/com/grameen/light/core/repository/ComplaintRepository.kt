package com.grameen.light.core.repository

import android.net.Uri
import com.grameen.light.core.data.toEntity
import com.grameen.light.core.data.toModel
import com.grameen.light.core.local.dao.ComplaintDao
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.model.PoleStatus
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import java.util.Locale

class ComplaintRepository(
    private val firestore: FirebaseFirestore = Firebase.firestore,
    private val storage: FirebaseStorage = Firebase.storage,
    private val complaintDao: ComplaintDao,
    private val poleRepository: PoleRepository
) {
    private companion object {
        const val STORAGE_TIMEOUT_MS = 12_000L
    }

    fun observeUserComplaints(userId: String): Flow<List<Complaint>> =
        complaintDao.observeByUser(userId).map { list -> list.map { it.toModel() } }

    fun observeAllComplaints(): Flow<List<Complaint>> =
        complaintDao.observeAll().map { list -> list.map { it.toModel() } }

    fun observeUserComplaintsRealtime(userId: String): Flow<Unit> = callbackFlow {
        val registration: ListenerRegistration = firestore.collection("complaints")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { _, _ ->
                trySend(Unit)
            }
        awaitClose { registration.remove() }
    }

    fun observeAllComplaintsRealtime(): Flow<Unit> = callbackFlow {
        val registration = firestore.collection("complaints")
            .addSnapshotListener { _, _ ->
                trySend(Unit)
            }
        awaitClose { registration.remove() }
    }

    suspend fun syncUserComplaints(userId: String): Result<Unit> = runCatching {
        val snapshot = firestore.collection("complaints")
            .whereEqualTo("userId", userId)
            .get()
            .await()
        val complaints = snapshot.documents.mapNotNull { doc -> parseComplaint(doc.data ?: return@mapNotNull null) }
        complaintDao.upsertAll(complaints.map { it.copy(synced = true).toEntity() })
    }

    suspend fun syncAllComplaints(): Result<Unit> = runCatching {
        val snapshot = firestore.collection("complaints").get().await()
        val complaints = snapshot.documents.mapNotNull { doc -> parseComplaint(doc.data ?: return@mapNotNull null) }
        complaintDao.upsertAll(complaints.map { it.copy(synced = true).toEntity() })
    }

    suspend fun submitComplaint(
        poleId: String,
        userId: String,
        issueType: String,
        description: String,
        imageUri: Uri?
    ): Result<String> = runCatching {
        val now = System.currentTimeMillis()
        val complaintId = generateComplaintId()
        val photoUrl: String? = null

        val status = ComplaintStatus.PENDING
        val complaint = Complaint(
            complaintId = complaintId,
            poleId = poleId,
            userId = userId,
            issueType = issueType,
            description = description,
            photoUrl = photoUrl,
            status = status,
            assignedWorker = null,
            createdAtMillis = now,
            updatedAtMillis = now,
            synced = true
        )

        val payload = hashMapOf(
            "complaintId" to complaint.complaintId,
            "poleId" to complaint.poleId,
            "userId" to complaint.userId,
            "issueType" to complaint.issueType,
            "description" to complaint.description,
            "photoUrl" to complaint.photoUrl,
            "status" to complaint.status.value,
            "assignedWorker" to complaint.assignedWorker,
            "createdAt" to Timestamp.now(),
            "updatedAt" to Timestamp.now()
        )

        firestore.collection("complaints").document(complaintId).set(payload).await()
        complaintDao.upsert(complaint.toEntity())

        if (imageUri != null) {
            uploadPhotoAndAttachAsync(complaintId, imageUri)
        }

        val poleStatus = mapIssueTypeToPoleStatus(issueType)
        runCatching {
            poleRepository.updatePoleStatus(poleId, poleStatus)
        }

        complaintId
    }

    suspend fun updateComplaintStatus(complaintId: String, status: ComplaintStatus, assignedWorker: String?): Result<Unit> = runCatching {
        val updates = mutableMapOf<String, Any?>(
            "status" to status.value,
            "updatedAt" to Timestamp.now(),
            "assignedWorker" to assignedWorker
        )
        firestore.collection("complaints").document(complaintId).update(updates).await()

        if (status == ComplaintStatus.FIXED) {
            val doc = firestore.collection("complaints").document(complaintId).get().await()
            val poleId = doc.getString("poleId").orEmpty()
            if (poleId.isNotEmpty()) {
                poleRepository.updatePoleStatus(poleId, PoleStatus.WORKING)
            }
        }
    }

    suspend fun complaintCountsByStatus(): Result<Map<ComplaintStatus, Int>> = runCatching {
        val snapshot = firestore.collection("complaints").get().await()
        val map = ComplaintStatus.entries.associateWith { 0 }.toMutableMap()
        snapshot.documents.forEach { doc ->
            val status = ComplaintStatus.fromValue(doc.getString("status"))
            map[status] = (map[status] ?: 0) + 1
        }
        map
    }

    private suspend fun uploadPhoto(complaintId: String, uri: Uri): Result<String> = runCatching {
        val ref = storage.reference.child("complaints/$complaintId.jpg")
        withTimeout(STORAGE_TIMEOUT_MS) {
            ref.putFile(uri).await()
            ref.downloadUrl.await().toString()
        }
    }

    private suspend fun generateComplaintId(): String {
        val latestLocal = complaintDao.getLatestComplaintId()?.substringAfter("CMP")?.toIntOrNull() ?: 0

        if (latestLocal > 0) {
            val next = latestLocal + 1
            return String.format(Locale.US, "CMP%03d", next)
        }

        val suffix = (System.currentTimeMillis() % 1_000_000).toInt()
        return String.format(Locale.US, "CMP%06d", suffix)
    }

    private fun uploadPhotoAndAttachAsync(complaintId: String, uri: Uri) {
        val ref = storage.reference.child("complaints/$complaintId.jpg")
        ref.putFile(uri)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                ref.downloadUrl
            }
            .addOnSuccessListener { downloadUri ->
                firestore.collection("complaints")
                    .document(complaintId)
                    .update(
                        mapOf(
                            "photoUrl" to downloadUri.toString(),
                            "updatedAt" to Timestamp.now()
                        )
                    )
            }
            .addOnFailureListener {
            }
    }

    private fun mapIssueTypeToPoleStatus(issueType: String): PoleStatus {
        val normalized = issueType.lowercase(Locale.getDefault())
        return when {
            normalized.contains("fused") -> PoleStatus.FUSED
            normalized.contains("daytime") -> PoleStatus.DAYTIME_ON
            normalized.contains("repair") || normalized.contains("wire") || normalized.contains("damaged") -> PoleStatus.UNDER_REPAIR
            else -> PoleStatus.UNDER_REPAIR
        }
    }

    private fun parseComplaint(data: Map<String, Any>): Complaint? {
        val complaintId = data["complaintId"] as? String ?: return null
        return Complaint(
            complaintId = complaintId,
            poleId = data["poleId"] as? String ?: "",
            userId = data["userId"] as? String ?: "",
            issueType = data["issueType"] as? String ?: "",
            description = data["description"] as? String ?: "",
            photoUrl = data["photoUrl"] as? String,
            status = ComplaintStatus.fromValue(data["status"] as? String),
            assignedWorker = data["assignedWorker"] as? String,
            createdAtMillis = (data["createdAt"] as? Timestamp)?.toDate()?.time ?: System.currentTimeMillis(),
            updatedAtMillis = (data["updatedAt"] as? Timestamp)?.toDate()?.time ?: System.currentTimeMillis(),
            synced = true
        )
    }
}

package com.grameen.light.core.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grameen.light.core.data.toEntity
import com.grameen.light.core.data.toModel
import com.grameen.light.core.local.dao.PoleDao
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class PoleRepository(
    private val firestore: FirebaseFirestore = Firebase.firestore,
    private val poleDao: PoleDao
) {
    fun observePoles(): Flow<List<Pole>> = poleDao.observeAll().map { list -> list.map { it.toModel() } }

    fun observePolesRealtime(): Flow<Unit> = callbackFlow {
        val registration = firestore.collection("poles")
            .addSnapshotListener { _, _ ->
                trySend(Unit)
            }
        awaitClose { registration.remove() }
    }

    suspend fun syncPoles(): Result<Unit> = runCatching {
        val snapshot = firestore.collection("poles").get().await()
        val poles = snapshot.documents.mapNotNull { doc ->
            val point = doc.getGeoPoint("location") ?: return@mapNotNull null
            Pole(
                poleId = doc.getString("poleId").orEmpty(),
                sector = doc.getString("sector").orEmpty(),
                controller = doc.getString("controller").orEmpty(),
                latitude = point.latitude,
                longitude = point.longitude,
                status = PoleStatus.fromValue(doc.getString("status")),
                lastReportedMillis = doc.getTimestamp("lastReported")?.toDate()?.time ?: System.currentTimeMillis()
            )
        }
        poleDao.upsertAll(poles.map { it.toEntity() })
    }

    suspend fun updatePoleStatus(poleId: String, status: PoleStatus): Result<Unit> = runCatching {
        firestore.collection("poles").document(poleId)
            .update(mapOf("status" to status.value, "lastReported" to Timestamp.now()))
            .await()
    }

    suspend fun seedPolesIfEmpty(): Result<Unit> = runCatching {
        val polesCollection = firestore.collection("poles")
        val existing = polesCollection.limit(1).get().await()
        if (!existing.isEmpty) return@runCatching

        val now = Timestamp.now()
        val seed = listOf(
            mapOf("poleId" to "P-101", "sector" to "Sector 1 Main Road", "controller" to "Zonal Unit 11", "location" to GeoPoint(12.9716, 77.5946), "status" to PoleStatus.WORKING.value, "lastReported" to now),
            mapOf("poleId" to "P-202", "sector" to "Sector 2 Temple Road", "controller" to "Zonal Unit 12", "location" to GeoPoint(12.9720, 77.5950), "status" to PoleStatus.FUSED.value, "lastReported" to now),
            mapOf("poleId" to "P-302", "sector" to "Sector 3 School Lane", "controller" to "Zonal Unit 13", "location" to GeoPoint(12.9725, 77.5955), "status" to PoleStatus.DAYTIME_ON.value, "lastReported" to now),
            mapOf("poleId" to "P-402", "sector" to "Sector 4 Greenfield Ave", "controller" to "Zonal Unit 14", "location" to GeoPoint(12.9730, 77.5960), "status" to PoleStatus.FUSED.value, "lastReported" to now),
            mapOf("poleId" to "P-502", "sector" to "Sector 5 North Gate", "controller" to "Zonal Unit 15", "location" to GeoPoint(12.9735, 77.5965), "status" to PoleStatus.WORKING.value, "lastReported" to now)
        )

        seed.forEach { pole ->
            val poleId = pole["poleId"] as String
            polesCollection.document(poleId).set(pole).await()
        }
    }
}

package com.grameen.light.core.repository

import com.grameen.light.core.model.AppUser
import com.grameen.light.core.model.UserRole
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = Firebase.auth,
    private val firestore: FirebaseFirestore = Firebase.firestore
) {
    fun currentUserId(): String? = auth.currentUser?.uid

    fun isLoggedIn(): Boolean = auth.currentUser != null

    suspend fun login(email: String, password: String): Result<AppUser> = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
        fetchCurrentUserProfile() ?: throw IllegalStateException("User profile not found")
    }

    suspend fun register(name: String, phone: String, email: String, password: String): Result<AppUser> = runCatching {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: throw IllegalStateException("User id missing")
        val payload = hashMapOf(
            "name" to name,
            "email" to email,
            "phone" to phone,
            "role" to UserRole.USER.value,
            "createdAt" to Timestamp.now()
        )
        firestore.collection("users").document(uid).set(payload).await()
        fetchCurrentUserProfile() ?: throw IllegalStateException("User profile creation failed")
    }

    suspend fun fetchCurrentUserProfile(): AppUser? {
        val uid = auth.currentUser?.uid ?: return null
        val doc = firestore.collection("users").document(uid).get().await()
        if (!doc.exists()) return null
        return AppUser(
            userId = uid,
            name = doc.getString("name").orEmpty(),
            email = doc.getString("email").orEmpty(),
            phone = doc.getString("phone").orEmpty(),
            role = if (doc.getString("role") == UserRole.ADMIN.value) UserRole.ADMIN else UserRole.USER,
            createdAtMillis = doc.getTimestamp("createdAt")?.toDate()?.time ?: System.currentTimeMillis()
        )
    }

    suspend fun updateProfile(name: String, phone: String): Result<Unit> = runCatching {
        val uid = auth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
        firestore.collection("users").document(uid)
            .update(mapOf("name" to name, "phone" to phone))
            .await()
    }

    suspend fun changePassword(newPassword: String): Result<Unit> = runCatching {
        val user = auth.currentUser ?: throw IllegalStateException("User not logged in")
        user.updatePassword(newPassword).await()
    }

    fun logout() {
        auth.signOut()
    }
}

package com.grameen.light.core.model

enum class UserRole(val value: String) {
    USER("user"),
    ADMIN("admin")
}

enum class PoleStatus(val value: String) {
    WORKING("working"),
    FUSED("fused"),
    DAYTIME_ON("daytime_on"),
    UNDER_REPAIR("under_repair");

    companion object {
        fun fromValue(value: String?): PoleStatus = entries.firstOrNull { it.value == value } ?: WORKING
    }
}

enum class ComplaintStatus(val value: String) {
    PENDING("pending"),
    ASSIGNED("assigned"),
    IN_PROGRESS("in_progress"),
    FIXED("fixed");

    companion object {
        fun fromValue(value: String?): ComplaintStatus = entries.firstOrNull { it.value == value } ?: PENDING
    }
}

data class AppUser(
    val userId: String,
    val name: String,
    val email: String,
    val phone: String,
    val role: UserRole,
    val createdAtMillis: Long
)

data class Pole(
    val poleId: String,
    val sector: String,
    val controller: String,
    val latitude: Double,
    val longitude: Double,
    val status: PoleStatus,
    val lastReportedMillis: Long
)

data class Complaint(
    val complaintId: String,
    val poleId: String,
    val userId: String,
    val issueType: String,
    val description: String,
    val photoUrl: String?,
    val status: ComplaintStatus,
    val assignedWorker: String?,
    val createdAtMillis: Long,
    val updatedAtMillis: Long,
    val synced: Boolean = true
)

package com.grameen.light.data.model

data class Complaint(
    val id: String,
    val poleId: String,
    val issueType: IssueType,
    val citizenName: String,
    val citizenMobile: String,
    val status: ComplaintStatus,
    val date: String,
    val imageUrl: String? = null
)

enum class IssueType {
    FUSED,
    NOT_WORKING,
    DAYTIME_ON
}

enum class ComplaintStatus {
    PENDING,
    ASSIGNED,
    FIXED
}

package com.grameen.light.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "complaints")
data class ComplaintEntity(
    @PrimaryKey val complaintId: String,
    val poleId: String,
    val userId: String,
    val issueType: String,
    val description: String,
    val photoUrl: String?,
    val status: String,
    val assignedWorker: String?,
    val createdAtMillis: Long,
    val updatedAtMillis: Long,
    val synced: Boolean
)

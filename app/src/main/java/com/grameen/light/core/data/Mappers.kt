package com.grameen.light.core.data

import com.grameen.light.core.local.entity.ComplaintEntity
import com.grameen.light.core.local.entity.PoleEntity
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus

fun PoleEntity.toModel(): Pole = Pole(
    poleId = poleId,
    sector = sector,
    controller = controller,
    latitude = latitude,
    longitude = longitude,
    status = PoleStatus.fromValue(status),
    lastReportedMillis = lastReportedMillis
)

fun Pole.toEntity(): PoleEntity = PoleEntity(
    poleId = poleId,
    sector = sector,
    controller = controller,
    latitude = latitude,
    longitude = longitude,
    status = status.value,
    lastReportedMillis = lastReportedMillis
)

fun ComplaintEntity.toModel(): Complaint = Complaint(
    complaintId = complaintId,
    poleId = poleId,
    userId = userId,
    issueType = issueType,
    description = description,
    photoUrl = photoUrl,
    status = ComplaintStatus.fromValue(status),
    assignedWorker = assignedWorker,
    createdAtMillis = createdAtMillis,
    updatedAtMillis = updatedAtMillis,
    synced = synced
)

fun Complaint.toEntity(): ComplaintEntity = ComplaintEntity(
    complaintId = complaintId,
    poleId = poleId,
    userId = userId,
    issueType = issueType,
    description = description,
    photoUrl = photoUrl,
    status = status.value,
    assignedWorker = assignedWorker,
    createdAtMillis = createdAtMillis,
    updatedAtMillis = updatedAtMillis,
    synced = synced
)

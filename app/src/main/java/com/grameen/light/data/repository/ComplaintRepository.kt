package com.grameen.light.data.repository

import com.grameen.light.data.model.Complaint
import com.grameen.light.data.model.ComplaintStatus
import com.grameen.light.data.model.IssueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class ComplaintRepository {
    
    private val complaints = mutableListOf<Complaint>()
    
    init {
        // Sample data
        complaints.addAll(
            listOf(
                Complaint(
                    id = "CMP001",
                    poleId = "Pole-001",
                    issueType = IssueType.FUSED,
                    citizenName = "Ramesh Kumar",
                    citizenMobile = "9876543210",
                    status = ComplaintStatus.PENDING,
                    date = "2024-01-15"
                ),
                Complaint(
                    id = "CMP002",
                    poleId = "Pole-005",
                    issueType = IssueType.NOT_WORKING,
                    citizenName = "Sita Devi",
                    citizenMobile = "9876543211",
                    status = ComplaintStatus.ASSIGNED,
                    date = "2024-01-14"
                ),
                Complaint(
                    id = "CMP003",
                    poleId = "Pole-010",
                    issueType = IssueType.DAYTIME_ON,
                    citizenName = "Mohan Singh",
                    citizenMobile = "9876543212",
                    status = ComplaintStatus.FIXED,
                    date = "2024-01-13"
                )
            )
        )
    }
    
    fun getAllComplaints(): Flow<List<Complaint>> = flowOf(complaints)
    
    fun getComplaintById(id: String): Complaint? {
        return complaints.find { it.id == id }
    }
    
    fun addComplaint(complaint: Complaint) {
        complaints.add(complaint)
    }
    
    fun updateComplaintStatus(id: String, status: ComplaintStatus) {
        val index = complaints.indexOfFirst { it.id == id }
        if (index != -1) {
            complaints[index] = complaints[index].copy(status = status)
        }
    }
    
    fun generateComplaintId(): String {
        return "CMP${String.format("%03d", complaints.size + 1)}"
    }
}

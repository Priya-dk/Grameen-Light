package com.grameen.light.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus
import com.grameen.light.core.repository.ComplaintRepository
import com.grameen.light.core.repository.PoleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class AdminMapUiState(
    val poles: List<Pole> = emptyList(),
    val recentAlerts: List<Complaint> = emptyList(),
    val infrastructureLoadPercent: Int = 0,
    val loading: Boolean = true,
    val error: String? = null
)

class AdminMapViewModel(
    private val poleRepository: PoleRepository,
    private val complaintRepository: ComplaintRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminMapUiState())
    val uiState: StateFlow<AdminMapUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            poleRepository.observePoles().collectLatest { poles ->
                val total = poles.size.coerceAtLeast(1)
                val fused = poles.count { it.status == PoleStatus.FUSED }
                val percent = (fused * 100) / total
                _uiState.value = _uiState.value.copy(
                    poles = poles,
                    infrastructureLoadPercent = percent,
                    loading = false
                )
            }
        }
        viewModelScope.launch {
            complaintRepository.observeAllComplaints().collectLatest { complaints ->
                _uiState.value = _uiState.value.copy(
                    recentAlerts = complaints.sortedByDescending { it.createdAtMillis }.take(5)
                )
            }
        }
        viewModelScope.launch {
            complaintRepository.observeAllComplaintsRealtime().collect {
                complaintRepository.syncAllComplaints()
            }
        }
        viewModelScope.launch {
            poleRepository.syncPoles()
            complaintRepository.syncAllComplaints()
        }
    }

    fun dispatchComplaint(complaintId: String, workerName: String, status: ComplaintStatus) {
        viewModelScope.launch {
            val result = complaintRepository.updateComplaintStatus(
                complaintId = complaintId,
                status = status,
                assignedWorker = workerName.takeIf { it.isNotBlank() }
            )
            if (result.isFailure) {
                _uiState.value = _uiState.value.copy(
                    error = result.exceptionOrNull()?.message ?: "Failed to update complaint"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    companion object {
        fun factory(
            poleRepository: PoleRepository,
            complaintRepository: ComplaintRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AdminMapViewModel(poleRepository, complaintRepository) as T
                }
            }
    }
}

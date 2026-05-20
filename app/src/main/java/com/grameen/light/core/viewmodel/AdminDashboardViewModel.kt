package com.grameen.light.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.model.Pole
import com.grameen.light.core.repository.ComplaintRepository
import com.grameen.light.core.repository.PoleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class AdminDashboardUiState(
    val totalComplaints: Int = 0,
    val pendingRepairs: Int = 0,
    val fixedLights: Int = 0,
    val energySaved: Int = 0,
    val recentAlerts: List<Complaint> = emptyList(),
    val polesById: Map<String, Pole> = emptyMap(),
    val loading: Boolean = true,
    val dispatchSuccess: Boolean = false,
    val error: String? = null
)

class AdminDashboardViewModel(
    private val complaintRepository: ComplaintRepository,
    private val poleRepository: PoleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminDashboardUiState())
    val uiState: StateFlow<AdminDashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
        observePoles()
        observeLocalData()
        observeRealtimeUpdates()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            try {
                poleRepository.seedPolesIfEmpty()
                complaintRepository.syncAllComplaints()
                poleRepository.syncPoles()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(loading = false, error = e.message)
            }
        }
    }

    private fun observePoles() {
        viewModelScope.launch {
            poleRepository.observePoles().collectLatest { poles ->
                _uiState.value = _uiState.value.copy(
                    polesById = poles.associateBy { it.poleId }
                )
            }
        }
    }

    private fun observeLocalData() {
        viewModelScope.launch {
            complaintRepository.observeAllComplaints().collectLatest { complaints ->
                val current = _uiState.value
                val total = complaints.size
                val pending = complaints.count { it.status == ComplaintStatus.PENDING }
                val fixed = complaints.count { it.status == ComplaintStatus.FIXED }
                val recent = complaints.sortedByDescending { it.createdAtMillis }.take(5)

                _uiState.value = current.copy(
                    totalComplaints = total,
                    pendingRepairs = pending,
                    fixedLights = fixed,
                    energySaved = fixed * 30,
                    recentAlerts = recent,
                    loading = false
                )
            }
        }
    }

    private fun observeRealtimeUpdates() {
        viewModelScope.launch {
            complaintRepository.observeAllComplaintsRealtime().collect {
                complaintRepository.syncAllComplaints()
            }
        }
    }

    fun dispatchComplaint(complaintId: String, workerName: String, status: ComplaintStatus) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val result = complaintRepository.updateComplaintStatus(
                complaintId = complaintId,
                status = status,
                assignedWorker = workerName.takeIf { it.isNotBlank() }
            )
            _uiState.value = if (result.isSuccess) {
                _uiState.value.copy(loading = false, dispatchSuccess = true)
            } else {
                _uiState.value.copy(
                    loading = false,
                    error = result.exceptionOrNull()?.message ?: "Failed to dispatch complaint"
                )
            }
        }
    }

    fun clearDispatchSuccess() {
        _uiState.value = _uiState.value.copy(dispatchSuccess = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    companion object {
        fun factory(
            complaintRepository: ComplaintRepository,
            poleRepository: PoleRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AdminDashboardViewModel(complaintRepository, poleRepository) as T
                }
            }
    }
}

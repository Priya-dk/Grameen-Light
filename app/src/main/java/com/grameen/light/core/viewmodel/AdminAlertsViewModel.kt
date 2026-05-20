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

data class AdminAlertsUiState(
    val complaints: List<Complaint> = emptyList(),
    val filteredComplaints: List<Complaint> = emptyList(),
    val polesById: Map<String, Pole> = emptyMap(),
    val selectedFilter: ComplaintStatus? = null,
    val loading: Boolean = true,
    val dispatchSuccess: Boolean = false,
    val error: String? = null
)

class AdminAlertsViewModel(
    private val complaintRepository: ComplaintRepository,
    private val poleRepository: PoleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminAlertsUiState())
    val uiState: StateFlow<AdminAlertsUiState> = _uiState.asStateFlow()

    init {
        loadComplaints()
        observePoles()
        observeRealtime()
    }

    private fun loadComplaints() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            complaintRepository.syncAllComplaints()
            poleRepository.syncPoles()
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

    private fun observeRealtime() {
        viewModelScope.launch {
            complaintRepository.observeAllComplaintsRealtime().collect {
                complaintRepository.syncAllComplaints()
            }
        }
        viewModelScope.launch {
            complaintRepository.observeAllComplaints().collectLatest { list ->
                val current = _uiState.value
                val filtered = if (current.selectedFilter != null) {
                    list.filter { it.status == current.selectedFilter }
                } else list
                _uiState.value = current.copy(
                    complaints = list,
                    filteredComplaints = filtered,
                    loading = false
                )
            }
        }
    }

    fun setFilter(filter: ComplaintStatus?) {
        val current = _uiState.value
        val filtered = if (filter != null) {
            current.complaints.filter { it.status == filter }
        } else current.complaints
        _uiState.value = current.copy(
            selectedFilter = filter,
            filteredComplaints = filtered
        )
    }

    fun dispatchComplaint(complaintId: String, workerName: String, status: ComplaintStatus) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val result = complaintRepository.updateComplaintStatus(complaintId, status, workerName)
            _uiState.value = if (result.isSuccess) {
                _uiState.value.copy(loading = false, dispatchSuccess = true)
            } else {
                _uiState.value.copy(loading = false, error = result.exceptionOrNull()?.message)
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
                    return AdminAlertsViewModel(complaintRepository, poleRepository) as T
                }
            }
    }
}

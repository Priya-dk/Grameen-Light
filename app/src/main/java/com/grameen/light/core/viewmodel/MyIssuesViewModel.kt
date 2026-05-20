package com.grameen.light.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.repository.AuthRepository
import com.grameen.light.core.repository.ComplaintRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class MyIssuesUiState(
    val loading: Boolean = true,
    val complaints: List<Complaint> = emptyList(),
    val openCount: Int = 0,
    val inProgressCount: Int = 0,
    val resolvedCount: Int = 0,
    val error: String? = null
)

class MyIssuesViewModel(
    private val authRepository: AuthRepository,
    private val complaintRepository: ComplaintRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyIssuesUiState())
    val uiState: StateFlow<MyIssuesUiState> = _uiState.asStateFlow()

    init {
        observeComplaints()
    }

    private fun observeComplaints() {
        viewModelScope.launch {
            val userId = authRepository.currentUserId() ?: run {
                _uiState.value = MyIssuesUiState(loading = false, error = "Please login again")
                return@launch
            }

            complaintRepository.syncUserComplaints(userId)

            launch {
                complaintRepository.observeUserComplaintsRealtime(userId).collect {
                    complaintRepository.syncUserComplaints(userId)
                }
            }

            complaintRepository.observeUserComplaints(userId).collectLatest { list ->
                _uiState.value = MyIssuesUiState(
                    loading = false,
                    complaints = list,
                    openCount = list.count { it.status == ComplaintStatus.PENDING },
                    inProgressCount = list.count { it.status == ComplaintStatus.ASSIGNED || it.status == ComplaintStatus.IN_PROGRESS },
                    resolvedCount = list.count { it.status == ComplaintStatus.FIXED }
                )
            }
        }
    }

    companion object {
        fun factory(authRepository: AuthRepository, complaintRepository: ComplaintRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MyIssuesViewModel(authRepository, complaintRepository) as T
                }
            }
    }
}

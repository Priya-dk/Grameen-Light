package com.grameen.light.core.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.grameen.light.core.repository.AuthRepository
import com.grameen.light.core.repository.ComplaintRepository
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

data class ReportIssueUiState(
    val loading: Boolean = false,
    val successComplaintId: String? = null,
    val error: String? = null
)

class ReportIssueViewModel(
    private val authRepository: AuthRepository,
    private val complaintRepository: ComplaintRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReportIssueUiState())
    val uiState: StateFlow<ReportIssueUiState> = _uiState.asStateFlow()

    fun submit(poleId: String, issueType: String, description: String, imageUri: Uri?) {
        viewModelScope.launch {
            val userId = authRepository.currentUserId()
            if (userId == null) {
                _uiState.value = ReportIssueUiState(error = "User session expired")
                return@launch
            }

            _uiState.value = ReportIssueUiState(loading = true)
            try {
                val result = withTimeout(SUBMIT_TIMEOUT_MS) {
                    complaintRepository.submitComplaint(
                        poleId = poleId,
                        userId = userId,
                        issueType = issueType,
                        description = description,
                        imageUri = imageUri
                    )
                }

                _uiState.value = result.fold(
                    onSuccess = { ReportIssueUiState(successComplaintId = it) },
                    onFailure = { ReportIssueUiState(error = it.message ?: "Failed to submit complaint") }
                )
            } catch (_: TimeoutCancellationException) {
                _uiState.value = ReportIssueUiState(error = "Request timed out. Please try again.")
            } catch (t: Throwable) {
                _uiState.value = ReportIssueUiState(error = t.message ?: "Failed to submit complaint")
            } finally {
                if (_uiState.value.loading) {
                    _uiState.value = ReportIssueUiState(error = "Failed to submit complaint")
                }
            }
        }
    }

    fun clearMessage() {
        _uiState.value = ReportIssueUiState()
    }

    companion object {
        private const val SUBMIT_TIMEOUT_MS = 60_000L

        fun factory(authRepository: AuthRepository, complaintRepository: ComplaintRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ReportIssueViewModel(authRepository, complaintRepository) as T
                }
            }
    }
}

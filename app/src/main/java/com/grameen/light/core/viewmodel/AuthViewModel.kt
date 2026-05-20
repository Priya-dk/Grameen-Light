package com.grameen.light.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.grameen.light.core.model.UserRole
import com.grameen.light.core.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val route: String? = null
)

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun checkSession() {
        viewModelScope.launch {
            if (!authRepository.isLoggedIn()) {
                _uiState.value = AuthUiState(route = "user_login")
                return@launch
            }
            val profile = authRepository.fetchCurrentUserProfile()
            val route = if (profile?.role == UserRole.ADMIN) "admin_dashboard" else "grameen_light_user_dashboard"
            _uiState.value = AuthUiState(route = route)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)
            val result = authRepository.login(email, password)
            result.fold(
                onSuccess = {
                    val route = if (it.role == UserRole.ADMIN) "admin_dashboard" else "grameen_light_user_dashboard"
                    _uiState.value = AuthUiState(route = route)
                },
                onFailure = {
                    _uiState.value = AuthUiState(error = "Invalid credentials")
                }
            )
        }
    }

    fun register(name: String, phone: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(loading = true)
            val result = authRepository.register(name, phone, email, password)
            _uiState.value = if (result.isSuccess) {
                AuthUiState(route = "grameen_light_user_dashboard")
            } else {
                AuthUiState(error = result.exceptionOrNull()?.message ?: "Registration failed")
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _uiState.value = AuthUiState(route = "user_login")
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    companion object {
        fun factory(repository: AuthRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(repository) as T
            }
        }
    }
}

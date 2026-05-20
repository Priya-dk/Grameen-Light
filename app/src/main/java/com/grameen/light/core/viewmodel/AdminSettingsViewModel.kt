package com.grameen.light.core.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grameen.light.core.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AdminSettingsUiState(
    val adminName: String = "Panchayat Office",
    val adminId: String = "ADMIN001",
    val adminContact: String = "",
    val darkMode: Boolean = false,
    val notifications: Boolean = true,
    val loggedOut: Boolean = false
)

class AdminSettingsViewModel(
    private val authRepository: AuthRepository,
    private val appContext: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminSettingsUiState())
    val uiState: StateFlow<AdminSettingsUiState> = _uiState.asStateFlow()

    private val prefs = appContext.getSharedPreferences("admin_prefs", Context.MODE_PRIVATE)

    init {
        loadPreferences()
    }

    private fun loadPreferences() {
        val adminName = prefs.getString("admin_name", "Panchayat Office").orEmpty()
        val adminContact = prefs.getString("admin_contact", "").orEmpty()
        val darkMode = prefs.getBoolean("dark_mode", false)
        val notifications = prefs.getBoolean("notifications", true)
        _uiState.value = _uiState.value.copy(
            adminName = adminName,
            adminContact = adminContact,
            darkMode = darkMode,
            notifications = notifications
        )
    }

    fun updateProfile(name: String, contact: String) {
        prefs.edit()
            .putString("admin_name", name.ifBlank { "Panchayat Office" })
            .putString("admin_contact", contact)
            .apply()
        _uiState.value = _uiState.value.copy(
            adminName = name.ifBlank { "Panchayat Office" },
            adminContact = contact
        )
    }

    fun toggleDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
        _uiState.value = _uiState.value.copy(darkMode = enabled)
        val nightMode = if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    fun toggleNotifications(enabled: Boolean) {
        prefs.edit().putBoolean("notifications", enabled).apply()
        _uiState.value = _uiState.value.copy(notifications = enabled)
    }

    fun logout() {
        authRepository.logout()
        _uiState.value = _uiState.value.copy(loggedOut = true)
    }

    companion object {
        fun factoryWithContext(authRepository: AuthRepository, context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AdminSettingsViewModel(authRepository, context.applicationContext) as T
                }
            }
    }
}

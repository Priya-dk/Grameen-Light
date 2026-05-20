package com.grameen.light.admin.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.grameen.light.MainActivity
import com.grameen.light.core.AppContainer
import com.grameen.light.core.viewmodel.AdminSettingsViewModel

class SettingsFragment : Fragment() {

    private val viewModel: AdminSettingsViewModel by viewModels {
        AdminSettingsViewModel.factoryWithContext(
            AppContainer.authRepository,
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AdminSettingsScreen(
                    viewModel = viewModel,
                    onLoggedOut = {
                        val intent = Intent(requireContext(), MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                        requireActivity().finish()
                    }
                )
            }
        }
    }
}

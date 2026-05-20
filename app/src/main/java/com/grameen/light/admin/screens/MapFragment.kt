package com.grameen.light.admin.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.grameen.light.core.AppContainer
import com.grameen.light.core.viewmodel.AdminMapViewModel

class MapFragment : Fragment() {

    private val viewModel: AdminMapViewModel by viewModels {
        AdminMapViewModel.factory(
            AppContainer.poleRepository,
            AppContainer.complaintRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AdminMapScreen(viewModel = viewModel)
            }
        }
    }
}

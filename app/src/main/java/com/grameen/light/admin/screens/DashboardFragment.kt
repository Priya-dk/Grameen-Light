package com.grameen.light.admin.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grameen.light.R
import com.grameen.light.core.AppContainer
import com.grameen.light.core.viewmodel.AdminDashboardViewModel

class DashboardFragment : Fragment() {

    private val viewModel: AdminDashboardViewModel by viewModels {
        AdminDashboardViewModel.factory(
            AppContainer.complaintRepository,
            AppContainer.poleRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AdminDashboardScreen(
                    viewModel = viewModel,
                    onOpenMap = {
                        activity?.findViewById<BottomNavigationView>(R.id.admin_bottom_nav)
                            ?.selectedItemId = R.id.nav_map
                    }
                )
            }
        }
    }
}

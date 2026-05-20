package com.grameen.light.user.screens

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grameen.light.core.AppContainer
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus
import com.grameen.light.core.viewmodel.MapViewModel

@Composable
fun LightsScreen(navController: NavController) {
    val viewModel: MapViewModel = viewModel(
        factory = MapViewModel.factory(
            AppContainer.poleRepository,
            AppContainer.networkMonitor.isOnline
        )
    )
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.seedIfNeeded()
        viewModel.refreshPoles()
    }

    val poles = uiState.poles.sortedByDescending { it.lastReportedMillis }
    val workingCount = poles.count { it.status == PoleStatus.WORKING }
    val issueCount = poles.count { it.status != PoleStatus.WORKING }

    Scaffold(
        topBar = {
            Surface(color = Color(0xFFF9F9F9)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lights",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B1B1B)
                    )
                    Text(
                        text = if (uiState.offline) "Offline" else "Online",
                        color = if (uiState.offline) Color(0xFFD32F2F) else Color(0xFF2E7D32),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        },
        bottomBar = {
            Surface(color = Color(0xFFF9F9F9)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LightsBottomNavItem(Icons.Default.LocationOn, "Map", false) {
                        navController.navigate("pole_map")
                    }
                    LightsBottomNavItem(Icons.Default.Home, "Lights", true) { }
                    LightsBottomNavItem(Icons.Default.Notifications, "Issues", false) {
                        navController.navigate("grameen_light_userissue")
                    }
                    LightsBottomNavItem(Icons.Default.Settings, "Settings", false) {
                        navController.navigate("user_profile")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F7F6))
                .padding(12.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SummaryCard("Total", poles.size.toString(), Color(0xFF1F2937), Modifier.weight(1f))
                SummaryCard("Working", workingCount.toString(), Color(0xFF2E7D32), Modifier.weight(1f))
                SummaryCard("Needs Fix", issueCount.toString(), Color(0xFFD32F2F), Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (uiState.loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF2E7D32))
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(poles, key = { it.poleId }) { pole ->
                        LightStatusCard(
                            pole = pole,
                            onReport = { navController.navigate("report_issue/${pole.poleId}") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryCard(title: String, value: String, valueColor: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            Text(text = title, color = Color(0xFF6B7280), fontSize = 12.sp)
            Text(text = value, color = valueColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun LightStatusCard(pole: Pole, onReport: () -> Unit) {
    val badge = statusBadge(pole.status)
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Pole ${pole.poleId}", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Box(
                    modifier = Modifier
                        .background(badge.first, RoundedCornerShape(999.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = pole.status.value.replace('_', ' ').uppercase(),
                        color = badge.second,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Sector: ${pole.sector}", color = Color(0xFF374151))
            Text("Controller: ${pole.controller}", color = Color(0xFF374151))
            Text(
                "Last reported: ${DateUtils.getRelativeTimeSpanString(pole.lastReportedMillis)}",
                color = Color(0xFF6B7280),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onReport() },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4A900)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Report Issue",
                    modifier = Modifier.padding(vertical = 10.dp).align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F1F1F)
                )
            }
        }
    }
}

@Composable
private fun LightsBottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val itemColor = if (selected) Color(0xFF5C6834) else Color(0xFF3C4A42)
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(
                if (selected) Color(0xFFD8E6A6) else Color.Transparent,
                RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = itemColor,
                modifier = Modifier.size(20.dp)
            )
            Text(text = label, fontSize = 12.sp, color = itemColor)
        }
    }
}

private fun statusBadge(status: PoleStatus): Pair<Color, Color> = when (status) {
    PoleStatus.FUSED -> Color(0xFFFFCDD2) to Color(0xFFD32F2F)
    PoleStatus.WORKING -> Color(0xFFC8E6C9) to Color(0xFF2E7D32)
    PoleStatus.DAYTIME_ON -> Color(0xFFFFF59D) to Color(0xFF4E3D00)
    PoleStatus.UNDER_REPAIR -> Color(0xFFBBDEFB) to Color(0xFF1565C0)
}

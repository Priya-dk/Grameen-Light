package com.grameen.light.admin.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.viewmodel.AdminDashboardViewModel

private data class MockAlert(
    val poleId: String,
    val issueType: String,
    val location: String
)

private val mockAlerts = listOf(
    MockAlert("P-402", "Fused Bulb", "Sector 4, Greenfield Ave"),
    MockAlert("P-118", "ON in Daytime", "Main Road, Near Panchayat Office"),
    MockAlert("P-219", "Flickering", "School Lane, Block B"),
    MockAlert("P-507", "Pole Damaged", "Market Street, Junction 2"),
    MockAlert("P-330", "Wire/Electrical issue", "Riverside Colony, Lane 3")
)

@Composable
fun AdminDashboardScreen(
    viewModel: AdminDashboardViewModel,
    onOpenMap: (String?) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var selectedComplaint by remember { mutableStateOf<Complaint?>(null) }

    if (selectedComplaint != null) {
        DispatchBottomSheet(
            complaint = selectedComplaint!!,
            onDismiss = { selectedComplaint = null },
            onUpdate = { workerName, status ->
                viewModel.dispatchComplaint(selectedComplaint!!.complaintId, workerName, status)
                selectedComplaint = null
            }
        )
    }

    Column(Modifier.fillMaxSize().background(Color(0xFFFAFAFA))) {
        DashboardHeader()
        if (uiState.loading && uiState.totalComplaints == 0) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF2E7D32))
            }
        } else {
            val hasRealData = uiState.totalComplaints > 0 || uiState.recentAlerts.isNotEmpty()
            val totalComplaints = if (hasRealData) uiState.totalComplaints else 24
            val pendingRepairs = if (hasRealData) uiState.pendingRepairs else 7
            val fixedLights = if (hasRealData) uiState.fixedLights else 15
            val energySaved = if (hasRealData) uiState.energySaved else 482

            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
                Text("Analytics Overview", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(Icons.Default.Info, Color(0xFF5D6360), "TOTAL COMPLAINTS", totalComplaints.toString(), Modifier.weight(1f))
                    StatCard(Icons.Default.Build, Color(0xFF7F7445), "PENDING REPAIRS", pendingRepairs.toString(), Modifier.weight(1f))
                }
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(Icons.Default.CheckCircle, Color(0xFF4CAF50), "FIXED LIGHTS", fixedLights.toString(), Modifier.weight(1f))
                    StatCard(Icons.Default.Info, Color(0xFFE1373B), "ENERGY SAVED (KWH)", energySaved.toString(), Modifier.weight(1f))
                }
                Spacer(Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Text("Recent Alerts", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
                    Text("View All", fontSize = 13.sp, color = Color(0xFF0F6C3A), fontWeight = FontWeight.Medium, modifier = Modifier.clickable { })
                }
                Spacer(Modifier.height(14.dp))

                if (hasRealData) {
                    uiState.recentAlerts.forEach { complaint ->
                        AlertCard(
                            poleId = "Pole ID: ${complaint.poleId}",
                            location = "${complaint.issueType} - ${complaint.description.take(50)}",
                            onNavigate = { onOpenMap(complaint.poleId) },
                            onDispatch = { selectedComplaint = complaint }
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                } else {
                    mockAlerts.forEach { mock ->
                        AlertCard(
                            poleId = "Pole ID: ${mock.poleId}",
                            location = "${mock.issueType} - ${mock.location}",
                            onNavigate = { onOpenMap(mock.poleId) },
                            onDispatch = { }
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable private fun DashboardHeader() {
    Surface(color = Color(0xFFFAFAFA)) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("\u26A1", fontSize = 20.sp)
                Text("VillageLight Admin", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20), fontFamily = FontFamily.SansSerif)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Icon(Icons.Default.Notifications, null, Modifier.size(24.dp), Color(0xFF232824))
                Box(Modifier.size(32.dp).clip(CircleShape).background(Color(0xFFD6DDD8)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, Modifier.size(20.dp), Color(0xFF1B5E20))
                }
            }
        }
    }
}

@Composable private fun StatCard(icon: ImageVector, iconTint: Color, label: String, value: String, modifier: Modifier) {
    Card(modifier.height(120.dp), colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(Modifier.fillMaxSize().padding(14.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Icon(icon, null, Modifier.size(20.dp), iconTint)
            Column {
                Text(label, fontSize = 10.sp, color = Color(0xFF757575), fontWeight = FontWeight.Medium, fontFamily = FontFamily.SansSerif)
                Text(value, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            }
        }
    }
}

@Composable fun AlertCard(poleId: String, location: String, onNavigate: () -> Unit = {}, onDispatch: () -> Unit = {}) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), elevation = CardDefaults.cardElevation(1.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier.width(4.dp).height(140.dp).background(Color(0xFFD32F2F)))
            Row(Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFFFCDD2)), contentAlignment = Alignment.Center) {
                    Text("\u26A0", color = Color(0xFFD32F2F), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Column(Modifier.weight(1f)) {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.Top) {
                        Column {
                            Text(poleId, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
                            Text(location, fontSize = 12.sp, color = Color(0xFF616161), lineHeight = 16.sp, fontFamily = FontFamily.SansSerif)
                        }
                        Box(Modifier.background(Color(0xFFFFCDD2), RoundedCornerShape(999.dp)).padding(horizontal = 10.dp, vertical = 4.dp)) {
                            Text("FUSED LIGHT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD32F2F), fontFamily = FontFamily.SansSerif)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Box(Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFF2E7D32)).clickable { onNavigate() }, contentAlignment = Alignment.Center) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, null, Modifier.size(14.dp), Color.White)
                                Text("Navigate", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.SansSerif)
                            }
                        }
                        Box(Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(8.dp)).background(Color.White).border(1.dp, Color(0xFF757575), RoundedCornerShape(8.dp)).clickable { onDispatch() }, contentAlignment = Alignment.Center) {
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Build, null, Modifier.size(14.dp), Color(0xFF424242))
                                Text("Dispatch", color = Color(0xFF424242), fontSize = 12.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.SansSerif)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable private fun DispatchBottomSheet(
    complaint: Complaint,
    onDismiss: () -> Unit,
    onUpdate: (String, ComplaintStatus) -> Unit
) {
    var workerName by remember { mutableStateOf(complaint.assignedWorker ?: "") }
    var selectedStatus by remember { mutableStateOf(ComplaintStatus.ASSIGNED) }
    val statuses = listOf(
        ComplaintStatus.PENDING,
        ComplaintStatus.ASSIGNED,
        ComplaintStatus.IN_PROGRESS,
        ComplaintStatus.FIXED
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White), modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(Modifier.padding(20.dp)) {
                Text("Dispatch Worker", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B))
                Spacer(Modifier.height(4.dp))
                Text("Complaint: ${complaint.complaintId}", fontSize = 14.sp, color = Color(0xFF616161))
                Spacer(Modifier.height(16.dp))

                Text("Worker Name:", fontSize = 14.sp, color = Color(0xFF616161))
                Spacer(Modifier.height(4.dp))
                OutlinedTextField(
                    value = workerName,
                    onValueChange = { workerName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Text("Status:", fontSize = 14.sp, color = Color(0xFF616161))
                Spacer(Modifier.height(4.dp))
                statuses.forEach { status ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedStatus = status }
                            .padding(vertical = 2.dp)
                    ) {
                        RadioButton(selected = selectedStatus == status, onClick = { selectedStatus = status })
                        Text(status.value, fontSize = 14.sp, color = Color(0xFF1B1B1B))
                    }
                }

                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text("Cancel", color = Color(0xFF757575), modifier = Modifier.clickable { onDismiss() }.padding(8.dp))
                    Spacer(Modifier.width(16.dp))
                    Text(
                        "Update",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable { onUpdate(workerName, selectedStatus) }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.viewmodel.AdminAlertsViewModel

@Composable
fun AdminAlertsScreen(
    viewModel: AdminAlertsViewModel,
    onOpenMap: (String?) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var selectedComplaint by remember { mutableStateOf<Complaint?>(null) }

    if (selectedComplaint != null) {
        DispatchDialog(
            complaint = selectedComplaint!!,
            onDismiss = { selectedComplaint = null },
            onUpdate = { worker, status ->
                viewModel.dispatchComplaint(selectedComplaint!!.complaintId, worker, status)
                selectedComplaint = null
            }
        )
    }

    Column(Modifier.fillMaxSize().background(Color(0xFFFAFAFA))) {
        Surface(color = Color(0xFFFAFAFA)) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text("Complaint Management", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            }
        }

        if (uiState.loading && uiState.filteredComplaints.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF2E7D32))
            }
        } else {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip("All", uiState.selectedFilter == null) { viewModel.setFilter(null) }
                    FilterChip("Pending", uiState.selectedFilter == ComplaintStatus.PENDING) { viewModel.setFilter(ComplaintStatus.PENDING) }
                    FilterChip("Assigned", uiState.selectedFilter == ComplaintStatus.ASSIGNED) { viewModel.setFilter(ComplaintStatus.ASSIGNED) }
                    FilterChip("Fixed", uiState.selectedFilter == ComplaintStatus.FIXED) { viewModel.setFilter(ComplaintStatus.FIXED) }
                }

                Spacer(Modifier.height(16.dp))

                uiState.filteredComplaints.forEach { complaint ->
                    ComplaintCard(
                        complaint = complaint,
                        onNavigate = { onOpenMap(complaint.poleId) },
                        onDispatch = { selectedComplaint = complaint }
                    )
                    Spacer(Modifier.height(12.dp))
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun FilterChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val bgColor = if (selected) Color(0xFF2E7D32) else Color(0xFFF3E5F5)
    val textColor = if (selected) Color.White else Color(0xFF424242)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(label, fontSize = 13.sp, color = textColor, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium, fontFamily = FontFamily.SansSerif)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ComplaintCard(complaint: Complaint, onNavigate: () -> Unit, onDispatch: () -> Unit) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), elevation = CardDefaults.cardElevation(1.dp)) {
        Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            Box(Modifier.width(4.dp).fillMaxHeight().background(Color(0xFFD32F2F)))
            Row(Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFFFCDD2)), contentAlignment = Alignment.Center) {
                    Text("\u26A0", color = Color(0xFFD32F2F), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Column(Modifier.weight(1f)) {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.Top) {
                        Column {
                            Text("Pole ID: ${complaint.poleId}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
                            Text(complaint.issueType, fontSize = 12.sp, color = Color(0xFF616161), fontFamily = FontFamily.SansSerif)
                            Text("ID: ${complaint.complaintId}", fontSize = 11.sp, color = Color(0xFF9E9E9E), fontFamily = FontFamily.SansSerif)
                        }
                        Box(Modifier.background(Color(0xFFFFCDD2), RoundedCornerShape(999.dp)).padding(horizontal = 10.dp, vertical = 4.dp)) {
                            Text(complaint.status.value.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD32F2F), fontFamily = FontFamily.SansSerif)
                        }
                    }

                    if (complaint.description.isNotBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Text(complaint.description, fontSize = 12.sp, color = Color(0xFF424242), fontFamily = FontFamily.SansSerif)
                    }

                    if (!complaint.photoUrl.isNullOrBlank()) {
                        Spacer(Modifier.height(10.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE0E0E0))
                        ) {
                            GlideImage(
                                model = complaint.photoUrl,
                                contentDescription = "Complaint photo",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else {
                        Spacer(Modifier.height(8.dp))
                        Text("No photo attached", fontSize = 11.sp, color = Color(0xFF9E9E9E), fontFamily = FontFamily.SansSerif)
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

@Composable
private fun DispatchDialog(complaint: Complaint, onDismiss: () -> Unit, onUpdate: (String, ComplaintStatus) -> Unit) {
    var workerName by remember { mutableStateOf(complaint.assignedWorker ?: "") }
    var selectedStatus by remember { mutableStateOf(complaint.status) }
    val statuses = listOf(ComplaintStatus.PENDING, ComplaintStatus.ASSIGNED, ComplaintStatus.IN_PROGRESS, ComplaintStatus.FIXED)

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
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { selectedStatus = status }.padding(vertical = 4.dp)) {
                        RadioButton(selected = selectedStatus == status, onClick = { selectedStatus = status })
                        Text(status.name, fontSize = 14.sp, color = Color(0xFF1B1B1B), modifier = Modifier.padding(start = 8.dp))
                    }
                }

                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text("Cancel", color = Color(0xFF757575), modifier = Modifier.clickable { onDismiss() }.padding(8.dp))
                    Spacer(Modifier.width(16.dp))
                    Text("Update", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onUpdate(workerName, selectedStatus) }.padding(8.dp))
                }
            }
        }
    }
}

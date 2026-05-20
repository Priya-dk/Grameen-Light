package com.grameen.light.admin.screens

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.grameen.light.R
import com.grameen.light.core.model.Complaint
import com.grameen.light.core.model.ComplaintStatus
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus
import com.grameen.light.core.viewmodel.AdminMapViewModel
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

object AdminMapFocus {
    @Volatile var pendingPoleId: String? = null
}

private val mockPoles = listOf(
    Pole("P-402", "Sector 4, Greenfield Ave", "CTRL-A1", 13.4358, 77.7318, PoleStatus.FUSED, System.currentTimeMillis() - 3_600_000),
    Pole("P-118", "Main Road, Panchayat Office", "CTRL-A1", 13.4350, 77.7305, PoleStatus.DAYTIME_ON, System.currentTimeMillis() - 7_200_000),
    Pole("P-219", "School Lane, Block B", "CTRL-B2", 13.4362, 77.7300, PoleStatus.WORKING, System.currentTimeMillis() - 1_800_000),
    Pole("P-507", "Market Street, Junction 2", "CTRL-B2", 13.4345, 77.7325, PoleStatus.UNDER_REPAIR, System.currentTimeMillis() - 5_400_000),
    Pole("P-330", "Riverside Colony, Lane 3", "CTRL-C3", 13.4370, 77.7330, PoleStatus.WORKING, System.currentTimeMillis() - 600_000),
    Pole("P-145", "Community Hall Road", "CTRL-C3", 13.4340, 77.7295, PoleStatus.WORKING, System.currentTimeMillis() - 900_000),
    Pole("P-612", "Temple Street", "CTRL-A1", 13.4365, 77.7340, PoleStatus.FUSED, System.currentTimeMillis() - 4_500_000),
    Pole("P-256", "Bus Stand", "CTRL-B2", 13.4352, 77.7335, PoleStatus.WORKING, System.currentTimeMillis() - 1_200_000)
)

private val OSM_HTTPS_TILE_SOURCE = XYTileSource(
    "OSM-HTTPS",
    0,
    19,
    256,
    ".png",
    arrayOf(
        "https://a.tile.openstreetmap.org/",
        "https://b.tile.openstreetmap.org/",
        "https://c.tile.openstreetmap.org/"
    )
)

@Composable
fun AdminMapScreen(viewModel: AdminMapViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    var selectedPole by remember { mutableStateOf<Pole?>(null) }
    var selectedComplaint by remember { mutableStateOf<Complaint?>(null) }
    var mapViewRef by remember { mutableStateOf<MapView?>(null) }

    if (selectedComplaint != null) {
        MapDispatchDialog(
            complaint = selectedComplaint!!,
            onDismiss = { selectedComplaint = null },
            onUpdate = { worker, status ->
                viewModel.dispatchComplaint(selectedComplaint!!.complaintId, worker, status)
                selectedComplaint = null
            }
        )
    }

    DisposableEffect(lifecycleOwner, mapViewRef) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapViewRef?.onResume()
                Lifecycle.Event.ON_PAUSE -> mapViewRef?.onPause()
                Lifecycle.Event.ON_DESTROY -> mapViewRef?.onDetach()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            mapViewRef?.onResume()
        }
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(Modifier.fillMaxSize().background(Color(0xFFFAFAFA))) {
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

        if (uiState.loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF2E7D32))
            }
        } else {
            val realPoleIds = uiState.poles.map { it.poleId }.toSet()
            val poles = uiState.poles + mockPoles.filter { it.poleId !in realPoleIds }

            LaunchedEffect(poles, mapViewRef) {
                val targetId = AdminMapFocus.pendingPoleId
                if (targetId != null) {
                    val target = poles.firstOrNull { it.poleId == targetId }
                    if (target != null) {
                        mapViewRef?.controller?.animateTo(GeoPoint(target.latitude, target.longitude))
                        mapViewRef?.controller?.setZoom(19.0)
                        selectedPole = target
                        AdminMapFocus.pendingPoleId = null
                    }
                }
            }

            Column(Modifier.fillMaxSize().padding(16.dp)) {
                Text("Geographic Status", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
                Spacer(Modifier.height(16.dp))

                Card(Modifier.fillMaxWidth().height(350.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF111417))) {
                    Box(Modifier.fillMaxSize()) {
                        AndroidView(
                            modifier = Modifier.fillMaxSize(),
                            factory = { context ->
                                MapView(context).apply {
                                    setTileSource(OSM_HTTPS_TILE_SOURCE)
                                    setUseDataConnection(true)
                                    setMultiTouchControls(true)
                                    isTilesScaledToDpi = true
                                    zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
                                    controller.setZoom(17.0)
                                    controller.animateTo(GeoPoint(13.4355, 77.7315))
                                    onResume()
                                    mapViewRef = this
                                }
                            },
                            update = { map ->
                                map.setUseDataConnection(true)
                                map.setTileSource(OSM_HTTPS_TILE_SOURCE)
                                map.overlays.removeAll { it is Marker }
                                poles.forEach { pole ->
                                    val marker = Marker(map).apply {
                                        position = GeoPoint(pole.latitude, pole.longitude)
                                        title = pole.poleId
                                        icon = map.context.getDrawable(markerRes(pole.status))
                                        setOnMarkerClickListener { _, _ ->
                                            selectedPole = pole
                                            true
                                        }
                                    }
                                    map.overlays.add(marker)
                                }
                                map.invalidate()
                            }
                        )

                        Card(shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFEA)), modifier = Modifier.align(Alignment.TopStart).padding(12.dp).width(170.dp).height(72.dp)) {
                            Column(Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 8.dp), verticalArrangement = Arrangement.SpaceBetween) {
                                Text("Infrastructure Load", fontSize = 11.sp, color = Color(0xFF2E332E), fontWeight = FontWeight.Medium)
                                Box(Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(999.dp)).background(Color(0xFFC7D0C8))) {
                                    Box(Modifier.fillMaxHeight().fillMaxWidth(uiState.infrastructureLoadPercent / 100f).clip(RoundedCornerShape(999.dp)).background(Color(0xFF1CB26A)))
                                }
                                Text("${uiState.infrastructureLoadPercent}%", fontSize = 10.sp, color = Color(0xFF2E332E), modifier = Modifier.align(Alignment.End))
                            }
                        }

                        if (selectedPole != null) {
                            PoleOverlayCard(
                                pole = selectedPole!!,
                                onDispatch = {
                                    selectedComplaint = uiState.recentAlerts.firstOrNull { it.poleId == selectedPole!!.poleId }
                                },
                                onClose = { selectedPole = null },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(12.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
                Text("Pole Monitoring", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
                Spacer(Modifier.height(12.dp))

                PoleMonitoringStats(poles = poles)

                Spacer(Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🌍", fontSize = 24.sp)
                        Text("Live Village Map", fontSize = 14.sp, color = Color(0xFF757575), fontFamily = FontFamily.SansSerif)
                    }
                }

                Spacer(Modifier.height(16.dp))
                if (uiState.error != null) {
                    Text(uiState.error!!, color = Color(0xFFD32F2F), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun PoleOverlayCard(
    pole: Pole,
    onDispatch: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(pole.poleId, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = when (pole.status) {
                        PoleStatus.FUSED -> "FUSED"
                        PoleStatus.DAYTIME_ON -> "DAYTIME ON"
                        PoleStatus.UNDER_REPAIR -> "UNDER REPAIR"
                        else -> "WORKING"
                    },
                    color = if (pole.status == PoleStatus.FUSED) Color(0xFFD32F2F) else Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.height(4.dp))
            Text("Sector: ${pole.sector}", fontSize = 12.sp, color = Color(0xFF616161))
            Text("Controller: ${pole.controller}", fontSize = 12.sp, color = Color(0xFF616161))
            Text("Last reported: ${DateUtils.getRelativeTimeSpanString(pole.lastReportedMillis)}", fontSize = 12.sp, color = Color(0xFF616161))
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF2E7D32))
                        .clickable { onDispatch() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Dispatch", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F5F5))
                        .clickable { onClose() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Close", color = Color(0xFF424242))
                }
            }
        }
    }
}

@Composable
private fun PoleMonitoringStats(poles: List<Pole>) {
    val working = poles.count { it.status == PoleStatus.WORKING }
    val fused = poles.count { it.status == PoleStatus.FUSED }
    val daytimeOn = poles.count { it.status == PoleStatus.DAYTIME_ON }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        MonitoringStatCard("$working", "Working", Color(0xFFE8F5E9), Color(0xFF2E7D32), Modifier.weight(1f))
        MonitoringStatCard("$fused", "Fused", Color(0xFFFFEBEE), Color(0xFFD32F2F), Modifier.weight(1f))
        MonitoringStatCard("$daytimeOn", "Daytime ON", Color(0xFFE3F2FD), Color(0xFF00897B), Modifier.weight(1f))
    }
}

@Composable
private fun MonitoringStatCard(
    value: String,
    label: String,
    bgColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textColor)
            Text(label, fontSize = 12.sp, color = textColor, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun MapDispatchDialog(
    complaint: Complaint,
    onDismiss: () -> Unit,
    onUpdate: (String, ComplaintStatus) -> Unit
) {
    var workerName by remember { mutableStateOf(complaint.assignedWorker ?: "") }
    var selectedStatus by remember { mutableStateOf(complaint.status) }
    val statuses = listOf(ComplaintStatus.PENDING, ComplaintStatus.ASSIGNED, ComplaintStatus.IN_PROGRESS, ComplaintStatus.FIXED)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("Dispatch Worker", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = workerName,
                    onValueChange = { workerName = it },
                    label = { Text("Worker Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                statuses.forEach { status ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedStatus = status }
                            .padding(vertical = 2.dp)
                    ) {
                        RadioButton(selected = selectedStatus == status, onClick = { selectedStatus = status })
                        Text(status.value, fontSize = 14.sp)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text("Cancel", modifier = Modifier.clickable { onDismiss() }.padding(8.dp), color = Color(0xFF757575))
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "Update",
                        modifier = Modifier.clickable { onUpdate(workerName, selectedStatus) }.padding(8.dp),
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

private fun markerRes(status: PoleStatus): Int = when (status) {
    PoleStatus.FUSED -> R.drawable.ic_marker_red
    PoleStatus.WORKING -> R.drawable.ic_marker_green
    PoleStatus.DAYTIME_ON -> R.drawable.ic_marker_yellow
    PoleStatus.UNDER_REPAIR -> R.drawable.ic_marker_blue
}

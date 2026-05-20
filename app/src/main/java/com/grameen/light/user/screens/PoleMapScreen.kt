package com.grameen.light.user.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.text.format.DateUtils
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grameen.light.R
import com.grameen.light.core.AppContainer
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus
import com.grameen.light.core.viewmodel.MapViewModel
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.tileprovider.tilesource.XYTileSource

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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PoleMapScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchFocusRequester = remember { FocusRequester() }
    val viewModel: MapViewModel = viewModel(
        factory = MapViewModel.factory(
            AppContainer.poleRepository,
            AppContainer.networkMonitor.isOnline
        )
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dummyPoles = remember { sampleDummyPoles() }
    var mapViewRef by remember { mutableStateOf<MapView?>(null) }
    var locationOverlay by remember { mutableStateOf<MyLocationNewOverlay?>(null) }
    var satelliteMode by remember { mutableStateOf(false) }
    var markersVisible by remember { mutableStateOf(true) }
    var showMapOptions by remember { mutableStateOf(false) }
    var showMenuSheet by remember { mutableStateOf(false) }
    var showLegendDialog by remember { mutableStateOf(false) }
    var searchFabClicks by remember { mutableStateOf(0) }

    val requestLocationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            mapViewRef?.let { map ->
                val overlay = locationOverlay ?: MyLocationNewOverlay(
                    GpsMyLocationProvider(context),
                    map
                ).also {
                    locationOverlay = it
                    map.overlays.add(it)
                }
                overlay.enableMyLocation()
                overlay.enableFollowLocation()
                overlay.runOnFirstFix {
                    val point = overlay.myLocation
                    if (point != null) {
                        map.post { map.controller.animateTo(point) }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.seedIfNeeded()
    }

    LaunchedEffect(searchFabClicks) {
        if (searchFabClicks > 0) {
            searchFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    LaunchedEffect(uiState.filteredPoles, uiState.searchText, mapViewRef) {
        val map = mapViewRef ?: return@LaunchedEffect
        val filteredDummyPoles = dummyPoles.filter { pole ->
            val statusMatch = uiState.statusFilter == null || pole.status == uiState.statusFilter
            val queryMatch = uiState.searchText.isBlank() ||
                pole.poleId.contains(uiState.searchText, ignoreCase = true) ||
                pole.sector.contains(uiState.searchText, ignoreCase = true)
            statusMatch && queryMatch
        }
        val visiblePoles = (uiState.filteredPoles + filteredDummyPoles).distinctBy { it.poleId }
        if (uiState.searchText.isNotBlank() && visiblePoles.size == 1) {
            val pole = visiblePoles.first()
            map.controller.animateTo(GeoPoint(pole.latitude, pole.longitude))
        }
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

    if (showMapOptions) {
        AlertDialog(
            onDismissRequest = { showMapOptions = false },
            title = { Text("Map Options") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Standard Map",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                satelliteMode = false
                                showMapOptions = false
                            }
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Satellite",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                satelliteMode = true
                                showMapOptions = false
                            }
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Show All",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                markersVisible = true
                                viewModel.setFilter(null)
                                viewModel.setSearch("")
                                showMapOptions = false
                            }
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Hide Markers",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                markersVisible = false
                                showMapOptions = false
                            }
                            .padding(vertical = 4.dp)
                    )
                }
            },
            confirmButton = {}
        )
    }

    if (showLegendDialog) {
        AlertDialog(
            onDismissRequest = { showLegendDialog = false },
            title = { Text("Map Legend") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Green = Working")
                    Text("Red = Fused")
                    Text("Yellow = Daytime ON")
                    Text("Blue = Under Repair")
                }
            },
            confirmButton = {
                Text(
                    text = "Close",
                    modifier = Modifier
                        .clickable { showLegendDialog = false }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        )
    }

    if (showMenuSheet) {
        ModalBottomSheet(onDismissRequest = { showMenuSheet = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val hasPermission = ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                            if (hasPermission) {
                                mapViewRef?.let { map ->
                                    val overlay = locationOverlay ?: MyLocationNewOverlay(
                                        GpsMyLocationProvider(context),
                                        map
                                    ).also {
                                        locationOverlay = it
                                        map.overlays.add(it)
                                    }
                                    overlay.enableMyLocation()
                                    overlay.enableFollowLocation()
                                    overlay.runOnFirstFix {
                                        val point = overlay.myLocation
                                        if (point != null) {
                                            map.post { map.controller.animateTo(point) }
                                        }
                                    }
                                }
                            } else {
                                requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                            }
                            showMenuSheet = false
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.MyLocation, contentDescription = null)
                    Text("My Location")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.refreshPoles()
                            showMenuSheet = false
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                    Text("Refresh Map")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showLegendDialog = true
                            showMenuSheet = false
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null)
                    Text("Map Legend")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    if (uiState.selectedPole != null) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.clearSelectedPole() }
        ) {
            PoleDetailsSheet(
                pole = uiState.selectedPole!!,
                onReport = {
                    uiState.selectedPole?.poleId?.let { poleId ->
                        viewModel.clearSelectedPole()
                        navController.navigate("report_issue/$poleId")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

    Scaffold(
        bottomBar = {
            Surface(color = Color(0xFFF9F9F9)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MapBottomNavItem(
                        icon = Icons.Default.LocationOn,
                        label = "Map",
                        isSelected = true,
                        onClick = { }
                    )
                    MapBottomNavItem(
                        icon = Icons.Default.Home,
                        label = "Lights",
                        isSelected = false,
                        onClick = { navController.navigate("user_home") }
                    )
                    MapBottomNavItem(
                        icon = Icons.Default.Notifications,
                        label = "Issues",
                        isSelected = false,
                        onClick = { navController.navigate("grameen_light_userissue") }
                    )
                    MapBottomNavItem(
                        icon = Icons.Default.Settings,
                        label = "Settings",
                        isSelected = false,
                        onClick = { navController.navigate("user_profile") }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    searchFabClicks += 1
                },
                containerColor = Color.White
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF0F1D2D))
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
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
                    val filteredDummyPoles = dummyPoles.filter { pole ->
                        val statusMatch = uiState.statusFilter == null || pole.status == uiState.statusFilter
                        val queryMatch = uiState.searchText.isBlank() ||
                            pole.poleId.contains(uiState.searchText, ignoreCase = true) ||
                            pole.sector.contains(uiState.searchText, ignoreCase = true)
                        statusMatch && queryMatch
                    }
                    val visiblePoles = (uiState.filteredPoles + filteredDummyPoles).distinctBy { it.poleId }
                    map.setUseDataConnection(true)
                    map.setTileSource(if (satelliteMode) TileSourceFactory.USGS_SAT else OSM_HTTPS_TILE_SOURCE)
                    map.overlays.removeAll { it is Marker }
                    if (markersVisible) {
                        visiblePoles.forEach { pole ->
                            val marker = Marker(map).apply {
                                position = GeoPoint(pole.latitude, pole.longitude)
                                title = pole.poleId
                                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                icon = markerDrawable(context, pole.status)
                                setOnMarkerClickListener { _, _ ->
                                    viewModel.selectPole(pole)
                                    map.controller.animateTo(position)
                                    true
                                }
                            }
                            map.overlays.add(marker)
                        }
                    }
                    map.invalidate()
                }
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { showMenuSheet = true }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                        Text(
                            text = "Village Lights",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF4CAF50))
                }

                Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                    OutlinedTextField(
                        value = uiState.searchText,
                        onValueChange = viewModel::setSearch,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(searchFocusRequester),
                        placeholder = { Text("Search Pole ID or Sector...") },
                        trailingIcon = {
                            IconButton(onClick = { showMapOptions = true }) {
                                Icon(Icons.Default.Settings, contentDescription = "Map settings")
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }

                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MapFilterChip(
                        label = "All Poles",
                        active = uiState.statusFilter == null,
                        onClick = {
                            markersVisible = true
                            viewModel.setFilter(null)
                        }
                    )
                    MapFilterChip(
                        label = "\u25CF Fused",
                        active = uiState.statusFilter == PoleStatus.FUSED,
                        onClick = {
                            markersVisible = true
                            viewModel.setFilter(PoleStatus.FUSED)
                        }
                    )
                    MapFilterChip(
                        label = "\u25CF Daytime ON",
                        active = uiState.statusFilter == PoleStatus.DAYTIME_ON,
                        onClick = {
                            markersVisible = true
                            viewModel.setFilter(PoleStatus.DAYTIME_ON)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun MapBottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val itemColor = if (isSelected) Color(0xFF5C6834) else Color(0xFF3C4A42)
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(
                if (isSelected) Color(0xFFD8E6A6) else Color.Transparent,
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
            Text(
                text = label,
                fontSize = 12.sp,
                color = itemColor
            )
        }
    }
}

@Composable
private fun MapFilterChip(
    label: String,
    active: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = if (active) Color(0xFF1E3A2F) else Color.White,
                shape = RoundedCornerShape(999.dp)
            ),
        shape = RoundedCornerShape(999.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (active) Color(0xFF1E3A2F) else Color.White
        )
    ) {
        Text(
            text = label,
            color = if (active) Color.White else Color(0xFF697178),
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun PoleDetailsSheet(pole: Pole, onReport: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Pole ID: ${pole.poleId}", fontWeight = FontWeight.Bold, fontSize = 28.sp)
                val colors = statusBadgeColors(pole.status)
                Card(colors = CardDefaults.cardColors(containerColor = colors.first)) {
                    Text(
                        text = pole.status.value.replace('_', ' ').uppercase(),
                        color = colors.second,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
            Text("Last reported: ${DateUtils.getRelativeTimeSpanString(pole.lastReportedMillis)}")
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(Modifier.padding(10.dp)) {
                        Text("SECTOR", color = Color(0xFF717171), fontSize = 12.sp)
                        Text(pole.sector, fontWeight = FontWeight.SemiBold)
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(Modifier.padding(10.dp)) {
                        Text("CONTROLLER", color = Color(0xFF717171), fontSize = 12.sp)
                        Text(pole.controller, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onReport() },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4A900))
            ) {
                Text(
                    text = "⚠ Report Issue",
                    color = Color(0xFF1A1A1A),
                    modifier = Modifier.padding(vertical = 14.dp).align(Alignment.CenterHorizontally)
                )
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

private fun markerDrawable(context: Context, status: PoleStatus) =
    ContextCompat.getDrawable(context, markerRes(status))?.apply {
        val width = (60 * context.resources.displayMetrics.density).toInt()
        val height = (80 * context.resources.displayMetrics.density).toInt()
        setBounds(0, 0, width, height)
    }

private fun statusBadgeColors(status: PoleStatus): Pair<Color, Color> = when (status) {
    PoleStatus.FUSED -> Color(0xFFFFCDD2) to Color(0xFFD32F2F)
    PoleStatus.WORKING -> Color(0xFFC8E6C9) to Color(0xFF2E7D32)
    PoleStatus.DAYTIME_ON -> Color(0xFFFFF59D) to Color(0xFF4E3D00)
    PoleStatus.UNDER_REPAIR -> Color(0xFFBBDEFB) to Color(0xFF1565C0)
}

private fun sampleDummyPoles(): List<Pole> {
    val now = System.currentTimeMillis()
    val centerLat = 13.4355
    val centerLon = 77.7315
    val statuses = listOf(
        PoleStatus.WORKING,
        PoleStatus.FUSED,
        PoleStatus.DAYTIME_ON,
        PoleStatus.UNDER_REPAIR
    )

    val poles = mutableListOf<Pole>()
    var index = 1
    for (latStep in -3..3) {
        for (lonStep in -3..3) {
            val status = statuses[(index - 1) % statuses.size]
            poles += Pole(
                poleId = "P-D${100 + index}",
                sector = "Sector ${latStep + 4}-${lonStep + 4}",
                controller = "Zonal Unit ${5 + ((index - 1) % 12)}",
                latitude = centerLat + (latStep * 0.0012),
                longitude = centerLon + (lonStep * 0.0012),
                status = status,
                lastReportedMillis = now - (index * 8L * 60L * 1000L)
            )
            index++
        }
    }
    return poles
}

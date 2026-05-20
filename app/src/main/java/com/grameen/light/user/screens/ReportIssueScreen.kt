package com.grameen.light.user.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grameen.light.core.AppContainer
import com.grameen.light.core.viewmodel.ReportIssueViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReportIssueScreen(
    navController: NavController,
    preselectedPoleId: String = "P-402"
) {
    val viewModel: ReportIssueViewModel = viewModel(
        factory = ReportIssueViewModel.factory(
            AppContainer.authRepository,
            AppContainer.complaintRepository
        )
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var selectedIssueType by remember { mutableStateOf("Fused Bulb") }
    var description by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(uiState.successComplaintId) {
        if (uiState.successComplaintId != null) {
            navController.navigate("grameen_light_userissue") {
                popUpTo("report_issue") { inclusive = true }
            }
            viewModel.clearMessage()
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }

    val issueTypes = listOf(
        "Fused Bulb" to "💡",
        "ON in Daytime" to "☀️",
        "Flickering" to "⚡",
        "Pole Damaged" to "🔧",
        "Wire/Electrical issue" to "⚠️"
    )

    Scaffold(
        containerColor = Color(0xFF0B0E13),
        bottomBar = {
            ReportBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF1C222C), RoundedCornerShape(10.dp))
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFFF2F4F7)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Report an Issue",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF2F4F7)
                    )
                    Text(
                        text = "Selected Pole: $preselectedPoleId",
                        fontSize = 11.sp,
                        color = Color(0xFF9AA6B5)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(Color(0xFF2B3340), RoundedCornerShape(999.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "U",
                        color = Color(0xFFF2F4F7),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFF8D116), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF161B22)),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "TARGET INFRASTRUCTURE",
                            color = Color(0xFF7E8591),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Pole ID: $preselectedPoleId",
                            color = Color(0xFFF8D116),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFF9AA6B5),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Sector 4, Greenfield Ave",
                                color = Color(0xFF9AA6B5),
                                fontSize = 11.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .background(Color(0xFF3B3521), RoundedCornerShape(999.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "💡", fontSize = 24.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Issue Type",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF2F4F7)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IssueTypeTile(
                    label = issueTypes[0].first,
                    emoji = issueTypes[0].second,
                    selected = selectedIssueType == issueTypes[0].first,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedIssueType = issueTypes[0].first }
                )
                IssueTypeTile(
                    label = issueTypes[1].first,
                    emoji = issueTypes[1].second,
                    selected = selectedIssueType == issueTypes[1].first,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedIssueType = issueTypes[1].first }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IssueTypeTile(
                    label = issueTypes[2].first,
                    emoji = issueTypes[2].second,
                    selected = selectedIssueType == issueTypes[2].first,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedIssueType = issueTypes[2].first }
                )
                IssueTypeTile(
                    label = issueTypes[3].first,
                    emoji = issueTypes[3].second,
                    selected = selectedIssueType == issueTypes[3].first,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedIssueType = issueTypes[3].first }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            IssueTypeTile(
                label = issueTypes[4].first,
                emoji = issueTypes[4].second,
                selected = selectedIssueType == issueTypes[4].first,
                modifier = Modifier.fillMaxWidth(),
                onClick = { selectedIssueType = issueTypes[4].first }
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Evidence Upload",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF2F4F7)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(118.dp)
                        .border(1.dp, Color(0xFFD7A92A), RoundedCornerShape(12.dp))
                        .clickable { imagePickerLauncher.launch("image/*") },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF171A21)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFFE5D8A8),
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Capture Photo",
                            color = Color(0xFFE5D8A8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(118.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A202A)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF29364B), Color(0xFF10141C))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (selectedImageUri == null) "Preview" else "Photo Selected",
                            color = Color(0xFFE7EDF6),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Additional Details (Optional)",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF2F4F7)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(132.dp),
                shape = RoundedCornerShape(14.dp),
                placeholder = {
                    Text(
                        text = "Briefly describe the situation for the repair crew...",
                        color = Color(0xFF7D8898)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF303949),
                    unfocusedBorderColor = Color(0xFF303949),
                    focusedContainerColor = Color(0xFF1A1D24),
                    unfocusedContainerColor = Color(0xFF1A1D24),
                    focusedTextColor = Color(0xFFF2F4F7),
                    unfocusedTextColor = Color(0xFFF2F4F7),
                    focusedPlaceholderColor = Color(0xFF7D8898),
                    unfocusedPlaceholderColor = Color(0xFF7D8898)
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFF6C80D), Color(0xFFFCE21D))
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        if (!uiState.loading) {
                            viewModel.submit(
                                poleId = preselectedPoleId,
                                issueType = selectedIssueType,
                                description = description,
                                imageUri = selectedImageUri
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "➤",
                        color = Color(0xFF554500),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (uiState.loading) "Submitting..." else "Submit Report",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4B3D00)
                    )
                }
            }

            if (uiState.error != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = uiState.error!!,
                    color = Color(0xFFFF8A80),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Report will be logged for technician review within 24 hours.",
                color = Color(0xFF8A909A),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun IssueTypeTile(
    label: String,
    emoji: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(98.dp)
            .clickable { onClick() }
            .border(
                1.dp,
                if (selected) Color(0xFFF8D116) else Color(0xFF1E2430),
                RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF171A21)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = emoji, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = label,
                color = if (selected) Color(0xFFF8D116) else Color(0xFFE7EDF6),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun ReportBottomBar(navController: NavController) {
    Surface(color = Color(0xFF16181D)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReportBottomNavItem(
                icon = Icons.Default.Home,
                label = "Dashboard",
                selected = false,
                onClick = { navController.navigate("grameen_light_user_dashboard") }
            )
            ReportBottomNavItem(
                icon = Icons.Default.Warning,
                label = "Complaints",
                selected = true,
                onClick = { }
            )
            ReportBottomNavItem(
                icon = Icons.Default.LocationOn,
                label = "Map",
                selected = false,
                onClick = { navController.navigate("grameen_light_user_dashboard") }
            )
            ReportBottomNavItem(
                icon = Icons.Default.Settings,
                label = "Profile",
                selected = false,
                onClick = { navController.navigate("user_profile") }
            )
        }
    }
}

@Composable
private fun ReportBottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (selected) Color(0xFF4A3B00) else Color(0xFFC7CDD8)
    Box(
        modifier = Modifier
            .background(
                if (selected) Color(0xFFF6D415) else Color.Transparent,
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = textColor,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = label,
                color = textColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

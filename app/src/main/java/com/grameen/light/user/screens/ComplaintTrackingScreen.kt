package com.grameen.light.user.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ComplaintTrackingScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFF9F9F9),
        topBar = { IssueTopBar() },
        bottomBar = { IssueBottomBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Issue Summary",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1B1B1B),
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SummaryChip(label = "Open", value = "1", chipColor = Color(0xFFFFE2E0), textColor = Color(0xFFBA1A1A))
                SummaryChip(label = "In Progress", value = "1", chipColor = Color(0xFFFFF2CC), textColor = Color(0xFF8A6A00))
                SummaryChip(label = "Resolved", value = "1", chipColor = Color(0xFFDDF4E8), textColor = Color(0xFF006C49))
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2A900), RoundedCornerShape(12.dp))
                    .clickable { navController.navigate("report_issue") }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Report an Issue",
                    color = Color(0xFF1B1B1B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2F5EC)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Total Reports",
                            fontSize = 13.sp,
                            color = Color(0xFF3C4A42),
                            fontFamily = FontFamily.SansSerif
                        )
                        Text(
                            text = "3",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1B1B1B),
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                    Text(
                        text = "Keep tracking your requests",
                        fontSize = 12.sp,
                        color = Color(0xFF6C7A71),
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Issue History",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1B1B),
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(16.dp))

            ComplaintDetailCard(
                complaintId = "CMP003",
                poleId = "Pole-010",
                issueType = "Burning in Daytime",
                status = "Fixed",
                statusColor = Color(0xFF006C49),
                date = "2024-01-13"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ComplaintDetailCard(
                complaintId = "CMP002",
                poleId = "Pole-005",
                issueType = "Not Working",
                status = "Assigned",
                statusColor = Color(0xFF8A6A00),
                date = "2024-01-14"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ComplaintDetailCard(
                complaintId = "CMP001",
                poleId = "Pole-001",
                issueType = "Fused",
                status = "Pending",
                statusColor = Color(0xFFBA1A1A),
                date = "2024-01-15"
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun SummaryChip(
    label: String,
    value: String,
    chipColor: Color,
    textColor: Color
) {
    Card(
        shape = RoundedCornerShape(999.dp),
        colors = CardDefaults.cardColors(containerColor = chipColor)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = textColor,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(Color.White.copy(alpha = 0.7f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

@Composable
private fun ComplaintDetailCard(
    complaintId: String,
    poleId: String,
    issueType: String,
    status: String,
    statusColor: Color,
    date: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F5EC)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = complaintId,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B1B1B),
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = date,
                        fontSize = 12.sp,
                        color = Color(0xFF6C7A71),
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            statusColor.copy(alpha = 0.1f),
                            RoundedCornerShape(999.dp)
                        )
                        .border(1.dp, statusColor.copy(alpha = 0.25f), RoundedCornerShape(999.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = statusColor,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Pole: $poleId",
                fontSize = 14.sp,
                color = Color(0xFF1B1B1B),
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Issue: $issueType",
                fontSize = 14.sp,
                color = Color(0xFF3C4A42),
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
private fun IssueTopBar() {
    Surface(color = Color(0xFFF9F9F9)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "My Issues",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1B1B),
                fontFamily = FontFamily.SansSerif
            )

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color(0xFF006C49)
            )
        }
    }
}

@Composable
private fun IssueBottomBar(navController: NavController) {
    Surface(color = Color(0xFFF9F9F9)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IssueNavItem(
                icon = Icons.Default.LocationOn,
                label = "Map",
                isSelected = false,
                onClick = { navController.navigate("grameen_light_user_dashboard") }
            )
            IssueNavItem(
                icon = Icons.Default.Home,
                label = "Lights",
                isSelected = false,
                onClick = { navController.navigate("user_home") }
            )
            IssueNavItem(
                icon = Icons.Default.Notifications,
                label = "Issues",
                isSelected = true,
                onClick = { }
            )
            IssueNavItem(
                icon = Icons.Default.Settings,
                label = "Settings",
                isSelected = false,
                onClick = { navController.navigate("user_profile") }
            )
        }
    }
}

@Composable
private fun IssueNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
                color = itemColor,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

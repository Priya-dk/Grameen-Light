package com.grameen.light.user.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun UserProfileScreen(navController: NavController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userName = currentUser?.displayName?.takeIf { it.isNotBlank() }
        ?: currentUser?.email?.substringBefore("@").orEmpty().replaceFirstChar { it.uppercase() }
            .ifBlank { "User" }

    var complaintAlerts by remember { mutableStateOf(true) }
    var assignmentAlerts by remember { mutableStateOf(true) }
    var energyReports by remember { mutableStateOf(false) }
    var darkMode by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = Color(0xFF0F150E),
        bottomBar = { UserSettingsBottomBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFDEE4D9),
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { navController.popBackStack() }
                )
                Text(
                    text = "Settings",
                    color = Color(0xFFDEE4D9),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1B211A)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(86.dp)
                            .border(3.dp, Color(0xFF78DC77), CircleShape)
                            .padding(5.dp)
                            .background(Color(0xFF2B322A), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color(0xFF78DC77),
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = userName, color = Color(0xFFDEE4D9), fontSize = 24.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .border(1.dp, Color(0xFF78DC77), RoundedCornerShape(999.dp))
                            .clickable { }
                            .padding(horizontal = 26.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Edit Profile", color = Color(0xFF78DC77), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }

            SettingsSectionTitle("ACCOUNT")
            SettingsGroupCard {
                SettingsActionRow(Icons.Default.Person, "Edit Profile")
                SettingsActionRow(Icons.Default.Settings, "Change Password")
                SettingsActionRow(Icons.Default.Notifications, "Linked Phone")
            }

            SettingsSectionTitle("NOTIFICATIONS")
            SettingsGroupCard {
                SettingsToggleRow("Complaint Alerts", complaintAlerts) { complaintAlerts = it }
                SettingsToggleRow("Assignment Alerts", assignmentAlerts) { assignmentAlerts = it }
                SettingsToggleRow("Energy Reports", energyReports) { energyReports = it }
            }

            SettingsSectionTitle("PREFERENCES")
            SettingsGroupCard {
                SettingsValueRow("Language", "English", Color(0xFF78DC77))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Dark Mode", color = Color(0xFFDEE4D9), fontSize = 15.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (darkMode) "ON" else "OFF",
                            color = Color(0xFFBECAB9),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Switch(
                            checked = darkMode,
                            onCheckedChange = { darkMode = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFF4CAF50),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFF30362E)
                            )
                        )
                    }
                }
                SettingsValueRow("Map Style", "Satellite", Color(0xFF78DC77))
            }

            SettingsSectionTitle("SUPPORT")
            SettingsGroupCard {
                SettingsActionRow(Icons.Default.Info, "Help & FAQ")
                SettingsActionRow(Icons.Default.Notifications, "Report a Bug")
                SettingsActionRow(Icons.Default.Home, "Contact Panchayat")
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4CAF50), RoundedCornerShape(10.dp))
                    .clickable {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("user_login") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Logout", color = Color(0xFF00390A), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFFFB4AB), RoundedCornerShape(10.dp))
                    .background(Color.Transparent, RoundedCornerShape(10.dp))
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Delete Account", color = Color(0xFFFFB4AB), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
private fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        color = Color(0xFFBECAB9),
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 14.dp, bottom = 6.dp, start = 2.dp)
    )
}

@Composable
private fun SettingsGroupCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B211A)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
    }
}

@Composable
private fun SettingsActionRow(icon: ImageVector, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 14.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color(0xFF78DC77), modifier = Modifier.size(18.dp))
            Text(text = label, color = Color(0xFFDEE4D9), fontSize = 16.sp, modifier = Modifier.padding(start = 12.dp))
        }
        Text(text = ">", color = Color(0xFFBECAB9), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun SettingsToggleRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color(0xFFDEE4D9), fontSize = 16.sp)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF4CAF50),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFF30362E)
            )
        )
    }
}

@Composable
private fun SettingsValueRow(title: String, value: String, valueColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, color = Color(0xFFDEE4D9), fontSize = 15.sp)
        Text(text = value, color = valueColor, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun UserSettingsBottomBar(navController: NavController) {
    Surface(color = Color(0xFF0A1009)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserSettingsBottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected = false,
                onClick = { navController.navigate("grameen_light_user_dashboard") }
            )
            UserSettingsBottomNavItem(
                icon = Icons.Default.LocationOn,
                label = "Map",
                selected = false,
                onClick = { navController.navigate("grameen_light_user_dashboard") }
            )
            UserSettingsBottomNavItem(
                icon = Icons.Default.Notifications,
                label = "Tracking",
                selected = false,
                onClick = { navController.navigate("grameen_light_userissue") }
            )
            UserSettingsBottomNavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                selected = true,
                onClick = { }
            )
        }
    }
}

@Composable
private fun UserSettingsBottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) Color(0xFF4CAF50) else Color.Transparent
    val textColor = if (selected) Color(0xFF00390A) else Color(0xFFBECAB9)

    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(999.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 11.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = label, tint = textColor, modifier = Modifier.size(18.dp))
            Text(text = label, color = textColor, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }
    }
}

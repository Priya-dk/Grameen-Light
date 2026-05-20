package com.grameen.light.admin.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.grameen.light.core.viewmodel.AdminSettingsViewModel

@Composable
fun AdminSettingsScreen(
    viewModel: AdminSettingsViewModel,
    onLoggedOut: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showProfileDialog by remember { mutableStateOf(false) }

    if (uiState.loggedOut) {
        LaunchedEffect(Unit) {
            onLoggedOut()
        }
    }

    if (showProfileDialog) {
        EditProfileDialog(
            currentName = uiState.adminName,
            currentContact = uiState.adminContact,
            onDismiss = { showProfileDialog = false },
            onSave = { name, contact ->
                viewModel.updateProfile(name, contact)
                showProfileDialog = false
            }
        )
    }

    Column(Modifier.fillMaxSize().background(Color(0xFFFAFAFA))) {
        Surface(color = Color(0xFFFAFAFA)) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text("Settings", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(80.dp).clip(CircleShape).background(Color(0xFF1B5E20)), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Person, null, Modifier.size(40.dp), Color.White)
            }
            Spacer(Modifier.height(12.dp))
            Text(uiState.adminName, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            Text("Admin ID: ${uiState.adminId}", fontSize = 14.sp, color = Color(0xFF616161), fontFamily = FontFamily.SansSerif)
            if (uiState.adminContact.isNotBlank()) {
                Text(uiState.adminContact, fontSize = 13.sp, color = Color(0xFF757575), fontFamily = FontFamily.SansSerif)
            }

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("Preferences", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            }
            Spacer(Modifier.height(12.dp))

            SettingsToggleRow("\u2699\uFE0F Dark Mode", uiState.darkMode) { viewModel.toggleDarkMode(it) }
            Spacer(Modifier.height(8.dp))
            SettingsToggleRow("\uD83D\uDD14 Notifications", uiState.notifications) { viewModel.toggleNotifications(it) }

            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("System", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            }
            Spacer(Modifier.height(12.dp))

            SettingsActionRow("\uD83D\uDC64 Panchayat Profile", Icons.Default.Settings) { showProfileDialog = true }
            Spacer(Modifier.height(8.dp))
            SettingsActionRow("\u2795 Logout", Icons.Default.ExitToApp) { viewModel.logout() }

            Spacer(Modifier.height(32.dp))
            Text("Grameen-Light Admin Portal v1.0.0", fontSize = 12.sp, color = Color(0xFF9E9E9E), fontStyle = FontStyle.Italic, fontFamily = FontFamily.SansSerif)
        }
    }
}

@Composable
private fun EditProfileDialog(
    currentName: String,
    currentContact: String,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var name by remember(currentName) { mutableStateOf(currentName) }
    var contact by remember(currentContact) { mutableStateOf(currentContact) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Panchayat Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1B1B))
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Office Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contact") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text("Cancel", color = Color(0xFF757575), modifier = Modifier.clickable { onDismiss() }.padding(8.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Save",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onSave(name, contact) }.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsToggleRow(label: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)), elevation = CardDefaults.cardElevation(0.dp)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Text(label, fontSize = 15.sp, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            Switch(
                checked = checked,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF4CAF50),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFBDBDBD)
                )
            )
        }
    }
}

@Composable
private fun SettingsActionRow(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(Modifier.fillMaxWidth().clickable { onClick() }, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)), elevation = CardDefaults.cardElevation(0.dp)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Text(label, fontSize = 15.sp, color = Color(0xFF1B1B1B), fontFamily = FontFamily.SansSerif)
            Icon(icon, null, Modifier.size(20.dp), Color(0xFF616161))
        }
    }
}

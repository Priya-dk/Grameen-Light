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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun UserHomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFF9F9F9),
        topBar = { UserMapTopBar() },
        bottomBar = {
            UserMapBottomBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(bottom = 220.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "My Location",
                    tint = Color(0xFF1B1B1B)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF10161A),
                                Color(0xFF1A2229),
                                Color(0xFF2B3227)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD).copy(alpha = 0.92f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color(0xFF3C4A42)
                        )
                        Text(
                            text = "Search Pole ID or Sector...",
                            fontSize = 14.sp,
                            color = Color(0xFF3C4A42),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null,
                            tint = Color(0xFF006C49)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    FilterChip(label = "All Poles")
                    FilterChip(
                        label = "Fused",
                        selected = true,
                        dotColor = Color(0xFFBA1A1A)
                    )
                    FilterChip(
                        label = "Daytime ON",
                        dotColor = Color(0xFFF4B400)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-20).dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.border(1.dp, Color(0xFFBA1A1A), RoundedCornerShape(10.dp))
                    ) {
                        Row(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                            Text(
                                text = "P-402:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            )
                            Text(
                                text = " FUSED",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFBA1A1A),
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .background(Color(0x33BA1A1A), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(0xFFBA1A1A), CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset(x = 48.dp, y = 120.dp)
                    .size(12.dp)
                    .background(Color(0xFF006C49), CircleShape)
                    .border(1.dp, Color.White.copy(alpha = 0.8f), CircleShape)
            )
            Box(
                modifier = Modifier
                    .offset(x = 140.dp, y = 260.dp)
                    .size(12.dp)
                    .background(Color(0xFF006C49), CircleShape)
                    .border(1.dp, Color.White.copy(alpha = 0.8f), CircleShape)
            )
            Box(
                modifier = Modifier
                    .offset(x = 260.dp, y = 70.dp)
                    .size(12.dp)
                    .background(Color(0xFFFF7886), CircleShape)
                    .border(1.dp, Color.White.copy(alpha = 0.8f), CircleShape)
            )

            PoleDetailSheet(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun UserMapTopBar() {
    Surface(color = Color(0xFFF9F9F9)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color(0xFF006C49)
                )
                Text(
                    text = "Village Lights",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B1B1B),
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color(0xFF006C49)
            )
        }
    }
}

@Composable
private fun FilterChip(
    label: String,
    selected: Boolean = false,
    dotColor: Color? = null
) {
    Row(
        modifier = Modifier
            .background(
                if (selected) Color(0x20BC0B3B) else Color.White,
                RoundedCornerShape(999.dp)
            )
            .border(
                1.dp,
                if (selected) Color(0xFFBA1A1A) else Color(0xFFBBCABF),
                RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (dotColor != null) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(dotColor, CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = label,
            fontSize = 14.sp,
            color = if (selected) Color(0xFF780021) else Color(0xFF1B1B1B),
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
private fun PoleDetailSheet(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD))
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(44.dp)
                    .height(4.dp)
                    .background(Color(0xFFBBCABF), RoundedCornerShape(999.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Pole ID: P-402",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B1B1B),
                        fontFamily = FontFamily.SansSerif
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF3C4A42),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Last reported: 2 hours ago",
                            fontSize = 14.sp,
                            color = Color(0xFF3C4A42),
                            modifier = Modifier.padding(start = 6.dp),
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFDAD6), RoundedCornerShape(999.dp))
                        .border(1.dp, Color(0x33BA1A1A), RoundedCornerShape(999.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "FUSED",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF93000A),
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoTile(
                    title = "SECTOR",
                    value = "North Village A-2",
                    modifier = Modifier.weight(1f)
                )
                InfoTile(
                    title = "CONTROLLER",
                    value = "Zonal Unit 12",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF59E0B))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Report Issue",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp),
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoTile(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = Color(0xFF3C4A42),
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1B1B),
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun UserMapBottomBar(navController: NavController) {
    Surface(color = Color(0xFFF9F9F9)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Default.LocationOn,
                label = "Map",
                isSelected = false,
                onClick = { navController.navigate("pole_map") }
            )
            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Lights",
                isSelected = true,
                onClick = { navController.navigate("pole_map") }
            )
            BottomNavItem(
                icon = Icons.Default.Notifications,
                label = "Issues",
                isSelected = false,
                onClick = { navController.navigate("grameen_light_userissue") }
            )
            BottomNavItem(
                icon = Icons.Default.Settings,
                label = "Settings",
                isSelected = false,
                onClick = { navController.navigate("user_profile") }
            )
        }
    }
}

@Composable
fun BottomNavItem(
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

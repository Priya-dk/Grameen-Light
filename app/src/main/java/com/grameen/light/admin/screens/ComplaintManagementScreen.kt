package com.grameen.light.admin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintManagementScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Complaint Management",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Filter Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(label = "All", selected = true)
                FilterChip(label = "Pending", selected = false)
                FilterChip(label = "Assigned", selected = false)
                FilterChip(label = "Fixed", selected = false)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Complaint List
            AdminComplaintCard(
                complaintId = "CMP003",
                poleId = "Pole-010",
                issueType = "Burning in Daytime",
                citizenName = "Mohan Singh",
                status = "Fixed",
                statusColor = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AdminComplaintCard(
                complaintId = "CMP002",
                poleId = "Pole-005",
                issueType = "Not Working",
                citizenName = "Sita Devi",
                status = "Assigned",
                statusColor = MaterialTheme.colorScheme.tertiary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AdminComplaintCard(
                complaintId = "CMP001",
                poleId = "Pole-001",
                issueType = "Fused",
                citizenName = "Ramesh Kumar",
                status = "Pending",
                statusColor = MaterialTheme.colorScheme.error
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AdminComplaintCard(
                complaintId = "CMP004",
                poleId = "Pole-015",
                issueType = "Fused",
                citizenName = "Lakshmi Devi",
                status = "Pending",
                statusColor = MaterialTheme.colorScheme.error
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            AdminComplaintCard(
                complaintId = "CMP005",
                poleId = "Pole-020",
                issueType = "Not Working",
                citizenName = "Rajesh Kumar",
                status = "Assigned",
                statusColor = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun FilterChip(label: String, selected: Boolean) {
    Box(
        modifier = Modifier
            .background(
                if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

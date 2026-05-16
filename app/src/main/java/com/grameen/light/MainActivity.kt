package com.grameen.light

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grameen.light.ui.theme.GrameenLightTheme
import com.grameen.light.user.screens.SplashScreen
import com.grameen.light.user.screens.UserLoginScreen
import com.grameen.light.user.screens.UserRegistrationScreen
import com.grameen.light.user.screens.UserHomeScreen
import com.grameen.light.user.screens.PoleMapScreen
import com.grameen.light.user.screens.ReportIssueScreen
import com.grameen.light.user.screens.ComplaintTrackingScreen
import com.grameen.light.user.screens.UserProfileScreen
import com.grameen.light.admin.screens.AdminLoginScreen
import com.grameen.light.admin.screens.AdminDashboardScreen
import com.grameen.light.admin.screens.ComplaintManagementScreen
import com.grameen.light.admin.screens.PoleMonitoringScreen
import com.grameen.light.admin.screens.AdminAnalyticsScreen
import com.grameen.light.admin.screens.AdminSettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrameenLightTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GrameenLightNavigation()
                }
            }
        }
    }
}

@Composable
fun GrameenLightNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        // Splash Screen
        composable("splash") {
            SplashScreen(navController = navController)
        }
        
        // User Flow
        composable("user_login") {
            UserLoginScreen(navController = navController)
        }
        
        composable("user_registration") {
            UserRegistrationScreen(navController = navController)
        }
        
        composable("user_home") {
            UserHomeScreen(navController = navController)
        }
        
        composable("pole_map") {
            PoleMapScreen(navController = navController)
        }
        
        composable("report_issue") {
            ReportIssueScreen(navController = navController)
        }
        
        composable("complaint_tracking") {
            ComplaintTrackingScreen(navController = navController)
        }
        
        composable("user_profile") {
            UserProfileScreen(navController = navController)
        }
        
        // Admin Flow
        composable("admin_login") {
            AdminLoginScreen(navController = navController)
        }
        
        composable("admin_dashboard") {
            AdminDashboardScreen(navController = navController)
        }
        
        composable("complaint_management") {
            ComplaintManagementScreen(navController = navController)
        }
        
        composable("pole_monitoring") {
            PoleMonitoringScreen(navController = navController)
        }
        
        composable("admin_analytics") {
            AdminAnalyticsScreen(navController = navController)
        }
        
        composable("admin_settings") {
            AdminSettingsScreen(navController = navController)
        }
    }
}

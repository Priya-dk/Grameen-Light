package com.grameen.light

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grameen.light.admin.AdminActivity
import com.grameen.light.core.AppContainer
import com.grameen.light.ui.theme.GrameenLightTheme
import com.grameen.light.user.screens.SplashScreen
import com.grameen.light.user.screens.UserLoginScreen
import com.grameen.light.user.screens.UserRegistrationScreen
import com.grameen.light.user.screens.LightsScreen
import com.grameen.light.user.screens.PoleMapScreen
import com.grameen.light.user.screens.ReportIssueScreen
import com.grameen.light.user.screens.ComplaintTrackingScreen
import com.grameen.light.user.screens.UserProfileScreen
import com.grameen.light.admin.screens.AdminLoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContainer.init(applicationContext)
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
            LightsScreen(navController = navController)
        }

        composable("grameen_light_user_dashboard") {
            PoleMapScreen(navController = navController)
        }
        
        composable("pole_map") {
            PoleMapScreen(navController = navController)
        }
        
        composable("report_issue") {
            ReportIssueScreen(navController = navController)
        }

        composable(
            route = "report_issue/{poleId}",
            arguments = listOf(navArgument("poleId") { type = NavType.StringType })
        ) { backStackEntry ->
            ReportIssueScreen(
                navController = navController,
                preselectedPoleId = backStackEntry.arguments?.getString("poleId") ?: "P-402"
            )
        }
        
        composable("complaint_tracking") {
            ComplaintTrackingScreen(navController = navController)
        }

        composable("grameen_light_userissue") {
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
            AdminActivityLauncher()
        }

        composable("grameen_light_admin_dashboard") {
            AdminActivityLauncher()
        }
    }
}

@Composable
private fun AdminActivityLauncher() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.startActivity(AdminActivity.createIntent(context))
    }
}

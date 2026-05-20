package com.grameen.light.user.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

@Composable
fun UserLoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val auth = FirebaseAuth.getInstance()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        // Illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🏘️",
                fontSize = 48.sp
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Branding
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "💡",
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Grameen-Light",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Login to Grameen-Light",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Access your digital village services and streetlight controls",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Email Input
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter your email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Password Input
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter your password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Login Button
        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Please enter email and password"
                    return@Button
                }

                val trimmedEmail = email.trim()
                val trimmedPassword = password.trim()
                val adminEmail = "admin@grameenlight.com"
                val adminPassword = "admin123"
                val normalizedEmail = trimmedEmail.lowercase().replace(".con", ".com")

                if (normalizedEmail == adminEmail) {
                    if (trimmedPassword == adminPassword) {
                        isLoading = true
                        // Ensure Firebase auth session so Firestore works for admin
                        auth.signInWithEmailAndPassword(adminEmail, adminPassword)
                            .addOnCompleteListener { signInTask ->
                                if (signInTask.isSuccessful) {
                                    isLoading = false
                                    navController.navigate("grameen_light_admin_dashboard") {
                                        popUpTo("user_login") { inclusive = true }
                                    }
                                } else {
                                    // Create admin account in Firebase if it doesn't exist
                                    auth.createUserWithEmailAndPassword(adminEmail, adminPassword)
                                        .addOnCompleteListener { createTask ->
                                            isLoading = false
                                            if (createTask.isSuccessful) {
                                                navController.navigate("grameen_light_admin_dashboard") {
                                                    popUpTo("user_login") { inclusive = true }
                                                }
                                            } else {
                                                errorMessage = "Admin login failed: ${createTask.exception?.message}"
                                            }
                                        }
                                }
                            }
                    } else {
                        errorMessage = "Incorrect admin password. Use admin123"
                    }
                    return@Button
                }

                if (trimmedEmail.lowercase().startsWith("admin@grameenlight")) {
                    errorMessage = "Use admin email: admin@grameenlight.com"
                    return@Button
                }
                
                isLoading = true
                auth.signInWithEmailAndPassword(trimmedEmail, trimmedPassword)
                    .addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            navController.navigate("grameen_light_user_dashboard") {
                                popUpTo("user_login") { inclusive = true }
                            }
                        } else {
                            errorMessage = when (task.exception) {
                                is FirebaseAuthException -> {
                                    val errorCode = (task.exception as FirebaseAuthException).errorCode
                                    when (errorCode) {
                                        "ERROR_INVALID_EMAIL" -> "Invalid email format"
                                        "ERROR_USER_NOT_FOUND" -> "User not found. Please register"
                                        "ERROR_WRONG_PASSWORD" -> "Incorrect password"
                                        "ERROR_USER_DISABLED" -> "This account has been disabled"
                                        else -> "Login failed: ${task.exception?.message}"
                                    }
                                }
                                else -> "Login failed"
                            }
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(28.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                Text("Loading...")
            } else {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                fontSize = 12.sp,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Register Text
        Text(
            text = "Are you a new user? Please register",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate("user_registration")
            }
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Footer
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "VILLAGE INFRASTRUCTURE STEWARDSHIP",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(50))
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(50))
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(50))
                )
            }
        }
    }
}

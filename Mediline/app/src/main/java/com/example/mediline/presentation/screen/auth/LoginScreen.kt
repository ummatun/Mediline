// File: presentation/screen/auth/LoginScreen.kt
package com.example.mediline.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediline.presentation.screen.auth.components.AuthTextField
import androidx.compose.material.icons.filled.Facebook
@Composable
fun LoginScreen(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        // ← Back Button (পুরো বামে)
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF0066FF))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Welcome Back!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A1A1A))
        Text("Log in to continue", fontSize = 16.sp, color = Color(0xFF888888))

        Spacer(modifier = Modifier.height(50.dp))

        // Email
        AuthTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email or Phone",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            isPassword = true,
//            showPassword = showPassword,
//            onPasswordToggleClick = { showPassword = !showPassword }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = {}, modifier = Modifier.align(Alignment.End)) {
            Text("Forgot Password?", color = Color(0xFF0066FF))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066FF))
        ) {
            Text("Log In", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Or sign up with
        Text("Or sign up with", color = Color(0xFF888888), modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(20.dp))

        // Google + Facebook (ছোট বক্স – তোর আগের SocialButton ব্যবহার করছি)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF8FAFF))
                    .border(2.dp, Color(0xFFE8F0FE), RoundedCornerShape(16.dp))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.Email, contentDescription = "Google", tint = Color.Unspecified, modifier = Modifier.size(28.dp))
            }

            Spacer(modifier = Modifier.width(20.dp))

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF8FAFF))
                    .border(2.dp, Color(0xFFE8F0FE), RoundedCornerShape(16.dp))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Facebook, contentDescription = "Facebook", tint = Color.Unspecified, modifier = Modifier.size(28.dp))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Don't have an account? Sign Up
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Don't have an account? ", color = Color(0xFF666666), fontSize = 15.sp)
            Text("Sign Up",
                color = Color(0xFF0066FF),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
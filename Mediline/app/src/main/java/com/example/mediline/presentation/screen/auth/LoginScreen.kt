// File: presentation/screen/auth/LoginScreen.kt
package com.example.mediline.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediline.presentation.screen.auth.components.AuthTextField

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState == "Login Successful") {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF0066FF))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Welcome Back!", fontSize = 28.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = Color(0xFF1A1A1A))
        Text("Log in to continue", fontSize = 16.sp, color = Color(0xFF888888))

        Spacer(modifier = Modifier.height(50.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Forgot Password Button
        TextButton(
            onClick = {
                if (email.isNotBlank()) {
                    authViewModel.sendPasswordReset(email)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Forgot Password?", color = Color(0xFF0066FF))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    authViewModel.login(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066FF))
        ) {
            Text("Log In", color = Color.White, fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (authState != null && authState != "Login Successful") {
            Text(
                text = authState ?: "",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text("Don't have an account? ", color = Color(0xFF666666), fontSize = 15.sp)
            Text(
                "Sign Up",
                color = Color(0xFF0066FF),
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

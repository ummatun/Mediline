// File: presentation/screen/auth/SignUpScreen.kt
package com.example.mediline.presentation.screen.auth
import androidx.compose.material.icons.outlined.Email
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediline.presentation.screen.auth.components.AuthTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
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

        // ← Back Button
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF0066FF))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Create Account", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A1A1A))
        Text("Fill your details to get started", fontSize = 16.sp, color = Color(0xFF888888))

        Spacer(modifier = Modifier.height(50.dp))

        // Full Name
        AuthTextField(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Full Name",
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email
        AuthTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email Address",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number
        AuthTextField(
            value = phone,
            onValueChange = { phone = it },
            placeholder = "Phone Number",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Create Password",
            isPassword = true,

        )

        Spacer(modifier = Modifier.height(32.dp))

        // Sign Up Button
        Button(
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066FF))
        ) {
            Text("Sign Up", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Or sign up with
        Text("Or sign up with", color = Color(0xFF888888), modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(20.dp))

        // Google + Facebook (ছোট বক্স)
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

        // Already have an account? Log In
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? ", color = Color(0xFF666666), fontSize = 15.sp)
            Text("Log In",
                color = Color(0xFF0066FF),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
package com.example.mediline.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navController: NavController,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Profile", color = Color(0xFF4A6CF7), fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = Color(0xFF4A6CF7))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            // Profile Picture with Edit Icon
            Box {
                AsyncImage(
                    model = uiState.profilePicUrl,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Photo",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 8.dp, y = 8.dp)
                        .size(36.dp)
                        .background(Color(0xFF4A6CF7), CircleShape)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Full Name
            ProfileTextField(
                label = "Full Name",
                value = uiState.fullName,
                onValueChange = { viewModel.updateName(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Number
            ProfileTextField(
                label = "Phone Number",
                value = uiState.phone,
                onValueChange = { viewModel.updatePhone(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            ProfileTextField(
                label = "Email",
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                enabled = false // Normally email change kore na
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Date of Birth
            ProfileTextField(
                label = "Date Of Birth",
                value = "DD / MM / YYYY",
                onValueChange = { },
                enabled = false,
                placeholder = true
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Update Button
            Button(
                onClick = { /* Save to Firebase */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A6CF7))
            ) {
                Text("Update Profile", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    placeholder: Boolean = false
) {
    Column {
        Text(label, fontSize = 14.sp, color = Color.Black.copy(alpha = 0.7f))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F7FF),
                    unfocusedContainerColor = Color(0xFFF5F7FF),
                    disabledContainerColor = Color(0xFFF5F7FF),

                    focusedBorderColor = Color(0xFFE3E8FF),
                    unfocusedBorderColor = Color(0xFFE3E8FF),
                    disabledBorderColor = Color(0xFFE3E8FF),

                    disabledTextColor = Color.Black.copy(alpha = 0.7f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                 ),
            enabled = enabled,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = if (placeholder) Color.Gray else Color.Black
            ),
            singleLine = true
        )
    }
}
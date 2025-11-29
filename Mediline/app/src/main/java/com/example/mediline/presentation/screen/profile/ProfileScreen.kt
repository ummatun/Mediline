package com.example.mediline.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue // Added this import
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.mediline.presentation.screen.navigation.Screen


data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val isLogout: Boolean = false
)

@Composable
fun ProfileMenuItem(item: MenuItem, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(if (item.isLogout) Color(0xFFFFEBEE) else Color(0xFFF0F4FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = if (item.isLogout) Color(0xFFE91E63) else Color(0xFF4A6CF7),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = item.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (item.isLogout) Color(0xFFE91E63) else Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
    }
}

// ProfileScreen — ekhn perfect
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navController: NavController,
    onEditProfileClick: () -> Unit = {}   // ← EI LINE TA ADD KOR (most important!)
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.OnboardingScreen.route) { inclusive = true } // Back এ ফিরে আসবে না
                        }
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FF))
                .padding(padding)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = uiState.profilePicUrl,
                            contentDescription = "Profile",
                            modifier = Modifier.size(110.dp).clip(CircleShape),
                            placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                            error = painterResource(android.R.drawable.ic_menu_gallery)
                        )
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(8.dp, 8.dp)
                                .size(32.dp)
                                .background(Color(0xFF4A6CF7), CircleShape)
                                .padding(6.dp)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    Text(uiState.fullName, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text(uiState.email, color = Color.Gray, fontSize = 14.sp)
                    Text(uiState.phone, color = Color.Gray, fontSize = 14.sp)
                }
            }

            val menuItems = listOf(
                MenuItem("Profile", Icons.Default.Person),
                MenuItem("Favorite", Icons.Default.FavoriteBorder),
                MenuItem("Payment Method", Icons.Outlined.CreditCard),
                MenuItem("Privacy Policy", Icons.Default.Lock),
                MenuItem("Settings", Icons.Default.Settings),
                MenuItem("Help", Icons.AutoMirrored.Filled.HelpOutline),
                MenuItem("Logout", Icons.AutoMirrored.Filled.Logout, isLogout = true)
            )

            items(menuItems.size) { index ->
                val item = menuItems[index]
                ProfileMenuItem(
                    item = item,
                    onClick = {
                        when (item.title) {
                            "Profile" -> {
                                navController.navigate(Screen.EditProfileScreen.route) {
                                }                            }
                            "Favorite" -> {
                                //navController.navigate(Screen.FavoriteDoctorsScreen.route)
                            }
                            "Payment Method" -> {
                                // পরে করবেন
                            }
                            "Privacy Policy" -> {
                                // পরে করবেন
                            }
                            "Settings" -> {
                                // পরে করবেন
                            }
                            "Help" -> {
                                // পরে করবেন
                            }
                            "Logout" -> {
                                viewModel.logout()
                                // যদি লগইন স্ক্রিনে পাঠাতে চান:
                                // navController.navigate(Screen.Login.route) {
                                //     popUpTo(0)
                                // }
                            }
                        }
                    }
                )
                if (index < menuItems.size - 1) {
                    HorizontalDivider(
                        color = Color.LightGray.copy(0.3f),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }

            item { Spacer(Modifier.height(100.dp)) }
        }
    }
}
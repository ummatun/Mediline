package com.example.mediline.presentation.screen.doctors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.mediline.presentation.screen.doctors.DoctorDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailScreen(
    viewModel: DoctorDetailViewModel = viewModel(),
    navController: NavController,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Doctor Info", color = Color(0xFF4A6CF7), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color(0xFF4A6CF7))
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More", tint = Color(0xFF4A6CF7))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                // Fixed: Icons.Filled use kora holo
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Filled.Home, "Home") })
                NavigationBarItem(selected = true,  onClick = { }, icon = { Icon(Icons.Filled.Search, "Search") })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Filled.Person, "Profile") })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Filled.CalendarMonth, "Calendar") })
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                uiState.error != null -> Text("Error: ${uiState.error}", color = Color.Red, modifier = Modifier.align(Alignment.Center))
                uiState.doctor != null -> {
                    val doctor = uiState.doctor!!


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF8F9FF))
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Sort By Row
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text("Sort By", color = Color.Gray, fontSize = 14.sp)
                            Spacer(Modifier.width(12.dp))
                            FilterChip(selected = true, onClick = { }, label = { Text("A-Z") })
                            FilterChip(selected = false, onClick = { }, label = { Text("Rating") })
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EEFF))
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(verticalAlignment = Alignment.Top) {
                                    AsyncImage(
                                        model = doctor.imageUrl,
                                        contentDescription = null,
                                        modifier = Modifier.size(110.dp).clip(CircleShape)
                                    )
                                    Spacer(Modifier.width(16.dp))
                                    Column {
                                        // Experience Badge
                                        Row(
                                            modifier = Modifier
                                                .background(Color(0xFF4A6CF7), RoundedCornerShape(20.dp))
                                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                        ) {
                                            // WorkHistory icon nai → Clock use kora holo
                                            Icon(Icons.Filled.Schedule, null, tint = Color.White, modifier = Modifier.size(16.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text("${doctor.experience} experience", color = Color.White, fontSize = 12.sp)
                                        }
                                        Spacer(Modifier.height(8.dp))
                                        Card(colors = CardDefaults.cardColors(Color(0xFF4A6CF7)), shape = RoundedCornerShape(16.dp)) {
                                            Text(
                                                "Focus: ${doctor.focus}",
                                                color = Color.White,
                                                fontSize = 13.sp,
                                                modifier = Modifier.padding(12.dp),
                                                lineHeight = 18.sp
                                            )
                                        }
                                    }
                                }

                                Spacer(Modifier.height(16.dp))
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Color(
                                        0xFFFFFFFF
                                    )
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp)  // ei khane padding hobe
                                    ) {
                                        Text(
                                            text = doctor.name,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = doctor.specialty,
                                            color = Color.Black.copy(alpha = 0.9f),
                                            fontSize = 14.sp
                                        )
                                    }
                                }

                                Spacer(Modifier.height(12.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(20.dp))
                                    Text(" ${doctor.rating}", fontWeight = FontWeight.Bold)
                                    Text(" (${doctor.reviews})", color = Color.Gray)
                                    Spacer(Modifier.width(16.dp))
                                    // AccessTime icon nai → Clock use kora
                                    Icon(Icons.Filled.Schedule, null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                                    Text(" ${doctor.availability}", color = Color.Gray)
                                }

                                Spacer(Modifier.height(16.dp))
                                Row {
                                    Button(
                                        onClick = { },
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(30.dp),
                                        colors = ButtonDefaults.buttonColors(Color(0xFF4A6CF7))
                                    ) {
                                        Icon(Icons.Filled.CalendarMonth, null)
                                        Spacer(Modifier.width(8.dp))
                                        Text("Schedule", fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(Modifier.width(12.dp))
                                    IconButton(onClick = { }) {
                                        Icon(Icons.Filled.Info, contentDescription = "More Info", tint = Color.Gray)
                                    }
                                    IconButton(onClick = { }) {
                                        Icon(Icons.Filled.Share, contentDescription = "Share Profile", tint = Color.Gray)
                                    }
                                    IconButton(onClick = { viewModel.toggleFavorite() }) {
                                        Icon(
                                            imageVector = if (doctor.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                            tint = if (doctor.isFavorite) Color(0xFFE91E63) else Color.Gray,
                                            contentDescription = "Favorite"
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))
                        InfoSection("Profile", doctor.profileText)
                        InfoSection("Career Path", doctor.careerPath)
                        InfoSection("Highlights", doctor.highlights)
                        Spacer(Modifier.height(100.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun InfoSection(title: String, text: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Spacer(Modifier.height(8.dp))
        Text(text, color = Color.Gray, fontSize = 14.sp, lineHeight = 22.sp)
        Spacer(Modifier.height(24.dp))
    }
}
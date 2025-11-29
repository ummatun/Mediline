// app/src/main/java/com/example/mediline/presentation/screen/doctors/DoctorsListScreen.kt
package com.example.mediline.presentation.screen.doctors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mediline.presentation.screen.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorsListScreen(
    viewModel: DoctorsViewModel = viewModel(),
    navController: NavController,
    onBackClick: () -> Unit = {},
    onDoctorClick: (Doctor) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Doctors", color = Color(0xFF4A6CF7), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.Black)
                    }
                },
                actions = {
                    // Correct + 100% working (Material3 compatible)
                    Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFF4A6CF7))
                    Icon(Icons.Filled.FilterList, contentDescription = "Filter", tint = Color(0xFF4A6CF7)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.Home, null) })
                NavigationBarItem(selected = true,  onClick = { }, icon = { Icon(Icons.Default.Search, null) })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.Person, null) })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.CalendarToday, null) })
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null) {
                Text("Error: ${uiState.error}", color = Color.Red, modifier = Modifier.align(Alignment.Center))
            } else {
                Column(modifier = Modifier.background(Color(0xFFF8F9FF))) {
                    // Sort By Row
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sort By", color = Color.Gray, fontSize = 14.sp)
                        Spacer(Modifier.width(12.dp))
                        FilterChip(selected = true, onClick = { }, label = { Text("A-Z") })
                        FilterChip(selected = false, onClick = { }, label = { Text("Rating") })
                        FilterChip(selected = false, onClick = { }, label = { Text("Heart") })
                        FilterChip(selected = false, onClick = { }, label = { Text("Gender") })
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.doctors) { doctor ->
                            DoctorCard(
                                doctor = doctor,
                                navController = navController,
                                onClick = { onDoctorClick(doctor) },
                                onFavoriteClick = { viewModel.toggleFavorite(doctor.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DoctorCard(
    doctor: Doctor,
    navController: NavController,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EEFF))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = doctor.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(90.dp).clip(CircleShape)
            )

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(doctor.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(doctor.specialty, color = Color.Gray, fontSize = 14.sp)

                Spacer(Modifier.height(12.dp))

                Row {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A6CF7)),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        Text(
                            "Info",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.DoctorDetailScreen.route)
                            }
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.BookmarkBorder, contentDescription = "Save", tint = Color.Gray)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.Gray)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Info, contentDescription = "Info", tint = Color.Gray)
                    }
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (doctor.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            tint = if (doctor.isFavorite) Color(0xFFE91E63) else Color.Gray,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }
        }
    }
}
package com.example.mediline.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediline.presentation.screen.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientHomeScreen(
    viewModel: PatientHomeViewModel = viewModel(),
            navController: NavController
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, null) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) })
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.Chatbot.route)
                    },
                    icon = { Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Chatbot") },
                    label = { Text("Chat") } // ঐচ্ছিক, চাইলে রাখুন
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {navController.navigate(Screen.ProfileScreen.route)},
                    icon = { Icon(Icons.Default.Person, null) })
                NavigationBarItem(
                    selected = false,
                    onClick = {navController.navigate(Screen.DoctorScheduleScreen.route)},
                    icon = { Icon(Icons.Default.CalendarToday, null) })
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FF))
                .padding(padding)
        ) {
            item { HeaderSection(uiState.patientName) }
            item{TabRowSection(navController = navController)}
            item { CalendarSection(uiState.appointmentDates) }
            item { uiState.todayAppointment?.let { TodayAppointmentSection(
                appointment = it,
                bookedTimeSlot = uiState.bookedTimeSlot  // 1 mane 10 AM
            ) } }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text("   Recommended Doctors", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(uiState.recommendedDoctors.size) { index ->
                DoctorCard(uiState.recommendedDoctors[index])
                Spacer(modifier = Modifier.height(12.dp))
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

// All small composables (Header, Calendar, Card etc.)
@Composable
fun TodayAppointmentSection(
    appointment: Appointment?,
    bookedTimeSlot: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EEFF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // "11 Wednesday - Today"
            Text(
                text = appointment?.dateText ?: "No appointment today",
                color = Color.Gray,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Time Slots (9 AM, 10 AM, 11 AM, 12 PM)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                val timeSlots = listOf("9 AM", "10 AM", "11 AM", "12 PM")
                timeSlots.forEachIndexed { index, time ->
                    val isBooked = index == bookedTimeSlot

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    if (isBooked) Color(0xFF4A6CF7) else Color.White
                                )
                                .border(
                                    1.5.dp,
                                    if (isBooked) Color(0xFF4A6CF7) else Color.LightGray,
                                    RoundedCornerShape(16.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = time,
                                color = if (isBooked) Color.White else Color.Black,
                                fontWeight = if (isBooked) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 15.sp
                            )
                        }

                        // Chhoto dot jodi booked hoy
                        if (isBooked) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF4A6CF7))
                            )
                        }
                    }
                }
            }

            // Jodi appointment thake → doctor info dekhabo
            appointment?.let {
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = it.time,  // "10 AM - 11 AM"
                        color = Color(0xFF4A6CF7),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(it.doctorName, fontWeight = FontWeight.Bold)
                        Text(it.specialty, color = Color.Gray, fontSize = 13.sp)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Close, null, tint = Color.Gray)
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}
@Composable
fun HeaderSection(name: String) {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = "https://api.dicebear.com/7.x/avataaars/png?seed=John",
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Hi, Welcome back", color = Color.Black, fontSize = 14.sp)
            Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}

@Composable
fun TabRowSection(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Doctors Tab
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable {navController.navigate(Screen.DoctorsListScreen.route) }
                .background(Color(0xFF4A6CF7))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                Icons.Default.LocalHospital, // Doctor icon
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Doctors",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Favorite Tab
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Chhoto heart icon (just upore)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF4A6CF7))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ){
            Icon(
                Icons.Default.Favorite,
                contentDescription = null,
                tint = Color(0xFFFF4081),
                modifier = Modifier.size(16.dp)

            )
            Spacer(modifier = Modifier.width(6.dp))

            Text("Favorite",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
        }}

        Spacer(modifier = Modifier.weight(1f))

        // Right side icons
        IconButton(onClick = {}) {
            Icon(Icons.Default.Share, null, tint = Color.Gray)
        }
        IconButton(onClick = {
            // এখানে সরাসরি নেভিগেট করব পরে
        }) {
            Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Chatbot", tint = Color(0xFF4A6CF7))
        }
    }
}

@Composable
fun CalendarSection(appointmentDates: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,  // ← Ei ta change kora must!
        verticalAlignment = Alignment.CenterVertically
    ) {
        val days = listOf("9\nMON", "10\nTUE", "11\nWED", "12\nTHU", "13\nFRI", "14\nSAT")

        val dateNumbers = listOf(9, 10, 11, 12, 13, 14)  // real date number
        days.forEachIndexed { index, day ->
            val date = dateNumbers[index]
            val hasAppointment = appointmentDates.contains(date)  // ← check kore
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (hasAppointment) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4A6CF7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "11\nWED",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Text(
                        text = day,
                        color = if (index < 2) Color.LightGray else Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = doctor.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(70.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(doctor.name, fontWeight = FontWeight.Bold)
                Text(doctor.specialty, color = Color.Gray, fontSize = 13.sp)
                Row {
                    Icon(Icons.Default.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(18.dp))
                    Text(" ${doctor.rating} (${doctor.reviews})", fontWeight = FontWeight.Bold)
                }
            }
            IconButton(onClick = {}) { Icon(Icons.Default.Info, null, tint = Color(0xFF4A6CF7)) }
            IconButton(onClick = {}) { Icon(Icons.Default.FavoriteBorder, null) }
        }
    }
}
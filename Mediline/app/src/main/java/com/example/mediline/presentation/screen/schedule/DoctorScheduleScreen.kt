package com.example.mediline.presentation.screen.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi

import androidx.compose.foundation.clickable
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
import coil.compose.AsyncImage

import java.util.*
import java.time.LocalDate // Changed from org.threeten.bp.LocalDate
import java.time.format.TextStyle // Changed from org.threeten.bp.format.TextStyle


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DoctorScheduleScreen(
    viewModel: DoctorScheduleViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    @OptIn(ExperimentalMaterial3Api::class)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Schedule", color = Color(0xFF4A6CF7), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Filled.Home, null) })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Filled.Search, null) })
                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Filled.Person, null) })
                NavigationBarItem(selected = true,  onClick = { }, icon = { Icon(Icons.Filled.CalendarMonth, null) })
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FF))
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))

            // Doctor Card
            Card(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row {
                        AsyncImage(
                            model = uiState.doctor.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Spacer(Modifier.width(16.dp))

                        Column {
                            Row(
                                modifier = Modifier
                                    .background(Color(0xFF4A6CF7))
                                    .clip(RoundedCornerShape(20.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Icon(Icons.Filled.WorkHistory, null, tint = Color.White, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("${uiState.doctor.experience} experience", color = Color.White, fontSize = 12.sp)
                            }
                            Spacer(Modifier.height(8.dp))
                            Card(colors = CardDefaults.cardColors(Color(0xFF4A6CF7)), shape = RoundedCornerShape(16.dp)) {
                                Text(
                                    "Focus: ${uiState.doctor.focus}",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            // Doctor Name + Specialty
                            Text(
                                text = uiState.doctor.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.MedicalServices,
                                    contentDescription = null,
                                    tint = Color(0xFF4A6CF7),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = uiState.doctor.specialty,
                                    color = Color(0xFF666666),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(20.dp))
                        Text(" ${uiState.doctor.rating}", fontWeight = FontWeight.Bold)
                        Text(" (${uiState.doctor.reviews})", color = Color.Gray)
                        Spacer(Modifier.width(16.dp))
                        Icon(Icons.Filled.AccessTime, null, tint = Color.Gray)
                        Text(" ${uiState.doctor.availability}", color = Color.Gray, fontSize = 13.sp)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Calendar Header
            Card(modifier = Modifier.padding(horizontal = 16.dp), colors = CardDefaults.cardColors(Color.White)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { viewModel.previousMonth() }) {
                            Icon(Icons.Filled.ChevronLeft, "Previous")
                        }
                        Text(
                            text = uiState.currentMonth.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + uiState.currentMonth.year,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        IconButton(onClick = { viewModel.nextMonth() }) {
                            Icon(Icons.Filled.ChevronRight, "Next")
                        }
                    }

                    // Week Days
                    Row(modifier = Modifier.fillMaxWidth()) {
                        listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN").forEach { day ->
                            Text(
                                text = day,
                                modifier = Modifier.weight(1f),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                color = if (day == "THU") Color(0xFF4A6CF7) else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))

                    // Calendar Grid
                    val daysInMonth = uiState.currentMonth.lengthOfMonth()
                    val firstDayOfMonth = uiState.currentMonth.atDay(1)
                    val firstDayWeekday = firstDayOfMonth.dayOfWeek.value // 1=Mon, 7=Sun

                    val rows = 6
                    val columns = 7
                    var dayCounter = 1

                    repeat(rows) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            repeat(columns) { col ->
                                val dayNumber = dayCounter - firstDayWeekday + 2
                                if (dayCounter < firstDayWeekday || dayNumber > daysInMonth) {
                                    Spacer(modifier = Modifier.weight(1f))
                                } else {
                                    val date = LocalDate.of(uiState.currentMonth.year, uiState.currentMonth.monthValue, dayNumber)
                                    val isSelected = date == uiState.selectedDate

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1f)
                                            .padding(4.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) Color(0xFF4A6CF7) else Color.Transparent)
                                            .clickable { viewModel.selectDate(date) },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = dayNumber.toString(),
                                            color = if (isSelected) Color.White else Color.Black,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }
                                if (col == columns - 1) dayCounter += 7
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Available Slots
            if (uiState.selectedDate != null) {
                Card(modifier = Modifier.padding(horizontal = 16.dp), colors = CardDefaults.cardColors(Color.White)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Available Time Slots", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(Modifier.height(12.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            uiState.availableSlots.forEach { slot ->
                                FilterChip(
                                    selected = false,
                                    onClick = { },
                                    label = { Text(slot) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = Color(0xFFE8EEFF),
                                        labelColor = Color(0xFF4A6CF7)
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(100.dp))
        }
    }
}
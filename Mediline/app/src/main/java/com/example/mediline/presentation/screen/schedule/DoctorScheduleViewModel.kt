// app/src/main/java/com/example/mediline/presentation/screen/schedule/DoctorScheduleViewModel.kt
package com.example.mediline.presentation.screen.schedule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.YearMonth

data class DoctorSchedule(
    val name: String = "Dr. Olivia Turner, M.D.",
    val specialty: String = "Dermato-Endocrinology",
    val experience: String = "20 years",
    val focus: String = "The impact of hormonal imbalances on skin conditions, specializing in acne, hirsutism, and other skin disorders.",
    val rating: Double = 4.5,
    val reviews: Int = 30,
    val availability: String = "Mon – Sat / 9 AM - 4 PM",
    val imageUrl: String = "https://xsgames.co/randomusers/avatar.php?g=female"
)

data class ScheduleUiState(
    val doctor: DoctorSchedule = DoctorSchedule(),
    val currentMonth: YearMonth = YearMonth.now(),
    val selectedDate: LocalDate? = null,
    val availableSlots: List<String> = listOf("09:00 AM", "10:30 AM", "02:00 PM", "03:30 PM")
)

class DoctorScheduleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    fun nextMonth() {
        _uiState.value = _uiState.value.copy(
            currentMonth = _uiState.value.currentMonth.plusMonths(1)
        )
    }

    fun previousMonth() {
        _uiState.value = _uiState.value.copy(
            currentMonth = _uiState.value.currentMonth.minusMonths(1)
        )
    }

    fun selectDate(date: LocalDate) {
        _uiState.value = _uiState.value.copy(selectedDate = date)
    }
}
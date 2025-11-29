
package com.example.mediline.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PatientHomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PatientHomeUiState())
    val uiState: StateFlow<PatientHomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = PatientHomeUiState(
                patientName = "John Doe",
                todayAppointment = Appointment(
                    time = "10 AM - 11 AM",
                    doctorName = "Dr. Olivia Turner, M.D.",
                    specialty = "Treatment and prevention of skin and photodermatitis.",
                    dateText = "11 Wednesday - Today"
                ) ,
                bookedTimeSlot = 1,
                recommendedDoctors = listOf(
                    Doctor("Dr. Olivia Turner, M.D.", "Dermato-Endocrinology", 5.0, 60, "https://xsgames.co/randomusers/avatar.php?g=female"),
                    Doctor("Dr. Alexander Bennett, Ph.D.", "Dermato-Genetics", 4.5, 40, "https://xsgames.co/randomusers/avatar.php?g=male"),
                    Doctor("Dr. Sophia Martinez, Ph.D.", "Cosmetic Bioengineering", 5.0, 150, "https://xsgames.co/randomusers/avatar.php?g=female"),
                    Doctor("Dr. Michael Davidson, M.D.", "Nano-Dermatology", 4.8, 90, "https://xsgames.co/randomusers/avatar.php?g=male")
                ),
                appointmentDates = listOf(11, 13, 14)  // Example: 11, 13, 14 tarik e appointment ache

            )
        }
    }
}

data class PatientHomeUiState(
    val patientName: String = "John Doe",
    val todayAppointment: Appointment? = null,
    val recommendedDoctors: List<Doctor> = emptyList(),
    val appointmentDates: List<Int> = listOf(11),           // 11 tarik e appointment
    val bookedTimeSlot: Int = 10
)

data class Appointment(
    val time: String,
    val doctorName: String,
    val specialty: String,
    val dateText: String
)

data class Doctor(
    val name: String,
    val specialty: String,
    val rating: Double,
    val reviews: Int,
    val imageUrl: String
)

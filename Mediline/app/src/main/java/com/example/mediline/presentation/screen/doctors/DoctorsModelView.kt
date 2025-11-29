// app/src/main/java/com/example/mediline/presentation/screen/doctors/DoctorsViewModel.kt
package com.example.mediline.presentation.screen.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class Doctor(
    val id: String = "",
    val name: String = "",
    val degree: String = "",
    val specialty: String = "",
    val imageUrl: String = "",
    val rating: Double = 4.8,
    val reviews: Int = 120,
    val isFavorite: Boolean = false
)

data class DoctorsUiState(
    val isLoading: Boolean = true,
    val doctors: List<Doctor> = emptyList(),
    val error: String? = null
)

class DoctorsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorsUiState())
    val uiState: StateFlow<DoctorsUiState> = _uiState.asStateFlow()

    init {
        loadDoctors()
    }

    private fun loadDoctors() {
        viewModelScope.launch {
            _uiState.value = DoctorsUiState(isLoading = true)

            // Simulate API call
            delay(800)

            val fakeDoctors = listOf(
                Doctor(
                    id = "1",
                    name = "Dr. Alexander Bennett, Ph.D.",
                    degree = "Ph.D.",
                    specialty = "Dermato-Genetics",
                    imageUrl = "https://xsgames.co/randomusers/avatar.php?g=male"
                ),
                Doctor(
                    id = "2",
                    name = "Dr. Michael Davidson, M.D.",
                    degree = "M.D.",
                    specialty = "Solar Dermatology",
                    imageUrl = "https://xsgames.co/randomusers/avatar.php?g=male"
                ),
                Doctor(
                    id = "3",
                    name = "Dr. Olivia Turner, M.D.",
                    degree = "M.D.",
                    specialty = "Dermato-Endocrinology",
                    imageUrl = "https://xsgames.co/randomusers/avatar.php?g=female"
                ),
                Doctor(
                    id = "4",
                    name = "Dr. Sophia Martinez, Ph.D.",
                    degree = "Ph.D.",
                    specialty = "Cosmetic Bioengineering",
                    imageUrl = "https://xsgames.co/randomusers/avatar.php?g=female",
                    isFavorite = true
                )
            )

            _uiState.value = DoctorsUiState(
                isLoading = false,
                doctors = fakeDoctors
            )
        }
    }

    fun toggleFavorite(doctorId: String) {
        _uiState.value = _uiState.value.copy(
            doctors = _uiState.value.doctors.map { doctor ->
                if (doctor.id == doctorId) {
                    doctor.copy(isFavorite = !doctor.isFavorite)
                } else doctor
            }
        )
    }

    fun refresh() {
        loadDoctors()
    }
}
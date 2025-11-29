// app/src/main/java/com/example/mediline/presentation/screen/doctor/DoctorDetailViewModel.kt
package com.example.mediline.presentation.screen.doctors

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DoctorDetail(
    val id: String = "",
    val name: String = "",
    val specialty: String = "",
    val experience: String = "",
    val focus: String = "",
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val availability: String = "",
    val profileText: String = "",
    val careerPath: String = "",
    val highlights: String = "",
    val imageUrl: String = "",
    val isFavorite: Boolean = false
)

data class DoctorDetailUiState(
    val isLoading: Boolean = true,
    val doctor: DoctorDetail? = null,
    val error: String? = null
)

class DoctorDetailViewModel(
    savedStateHandle: SavedStateHandle? = null
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorDetailUiState())
    val uiState: StateFlow<DoctorDetailUiState> = _uiState.asStateFlow()

    // Jodi navigation diye id pathas, tahole ei ta use korbi
    private val doctorId: String? = savedStateHandle?.get<String>("doctorId")

    init {
        loadDoctorDetail()
    }

    private fun loadDoctorDetail() {
        viewModelScope.launch {
            _uiState.value = DoctorDetailUiState(isLoading = true)

            // Simulate API delay
            delay(800)

            // Fake data — real app e Firebase/Firestore theke asbe
            val doctor = DoctorDetail(
                id = "1",
                name = "Dr. Alexander Bennett, Ph.D.",
                specialty = "Dermato-Genetics",
                experience = "15 years",
                focus = "The impact of hormonal imbalances on skin conditions, specializing in acne, hirsutism, and other skin disorders.",
                rating = 5.0,
                reviews = 40,
                availability = "Mon-Sat / 9:00AM - 5:00PM",
                profileText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                careerPath = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                highlights = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                imageUrl = "https://xsgames.co/randomusers/avatar.php?g=male",
                isFavorite = false
            )

            _uiState.value = DoctorDetailUiState(
                isLoading = false,
                doctor = doctor
            )
        }
    }

    fun toggleFavorite() {
        _uiState.value.doctor?.let { doc ->
            _uiState.value = _uiState.value.copy(
                doctor = doc.copy(isFavorite = !doc.isFavorite)
            )
        }
    }
}
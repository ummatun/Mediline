// app/src/main/java/com/example/mediline/presentation/screen/profile/ProfileViewModel.kt
package com.example.mediline.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val fullName: String = "John Doe",
    val email: String = "john.doe@example.com",
    val phone: String = "+880 1234567890",
    val profilePicUrl: String = "https://xsgames.co/randomusers/avatar.php?g=male",
    val isLoading: Boolean = false
)

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            // Ei khane Firebase/Auth/SharedPref theke data nibi
            // Example:
            // val user = firebaseAuth.currentUser
            // val name = user?.displayName ?: "John Doe"

            // Temporary fake data
            _uiState.value = ProfileUiState(
                fullName = "John Doe",
                email = "john.doe@example.com",
                phone = "+880 1712345678",
                profilePicUrl = "https://xsgames.co/randomusers/avatar.php?g=male"
            )

        }
    }

    fun logout() {
        // Firebase signOut + navigate to login
    }
    fun updateName(newName: String) {
        _uiState.value = _uiState.value.copy(fullName = newName)
    }

    fun updatePhone(newPhone: String) {
        _uiState.value = _uiState.value.copy(phone = newPhone)
    }

    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }
}

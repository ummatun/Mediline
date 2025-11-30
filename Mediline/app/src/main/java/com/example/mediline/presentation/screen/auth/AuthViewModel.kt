package com.example.mediline.presentation.screen.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<String?>(null)
    val authState: StateFlow<String?> = _authState

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = "Login Successful"
                } else {
                    _authState.value = task.exception?.localizedMessage
                }
            }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _authState.value =
                        if (task.isSuccessful) "Signup Successful"
                        else task.exception?.localizedMessage
                }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = null
    }

    fun sendPasswordReset(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = "Password reset email sent"
                } else {
                    _authState.value = task.exception?.localizedMessage
                }
            }
    }
}

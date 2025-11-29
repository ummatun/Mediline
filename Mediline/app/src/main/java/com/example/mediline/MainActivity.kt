package com.example.mediline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediline.presentation.screen.auth.LoginScreen
import com.example.mediline.ui.theme.MedilineTheme
import com.example.mediline.presentation.screen.auth.SignUpScreen
import com.example.mediline.presentation.screen.chatbot.ChatbotScreen
import com.example.mediline.presentation.screen.home.PatientHomeScreen
import com.example.medilineapp.presentation.screen.onboarding.OnboardingScreen
import com.example.medilineapp.presentation.screen.splash.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text // Make sure this is imported if using Material3
import androidx.compose.ui.Alignment
import com.example.mediline.presentation.screen.profile.EditProfileScreen

import com.example.mediline.presentation.screen.profile.ProfileScreen
import androidx.compose.runtime.mutableStateOf // <--- ADD THIS LINE
import androidx.compose.runtime.getValue // <--- ADD THIS LINE (for 'by' delegate)
import androidx.compose.runtime.setValue // <--- ADD THIS LINE (for 'by' delegate)
import androidx.navigation.compose.rememberNavController
import com.example.mediline.presentation.screen.doctors.DoctorDetailScreen
import com.example.mediline.presentation.screen.doctors.DoctorsListScreen
import com.example.mediline.presentation.screen.schedule.DoctorScheduleScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediline.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var showEditProfile by mutableStateOf(false)  // ← ei state add kor
        setContent {
            MedilineTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)  // এখানেই সব!
                }
            }
        }
    }
}


                    //ProfileScreen()
//                    if (showEditProfile) {
//                        EditProfileScreen(
//                            onBackClick = { showEditProfile = false }  // back e profile e asbe
//                        )
//                    } else {
//                        ProfileScreen(
//                            onEditProfileClick = { showEditProfile = true }  // ← ei function pass kor
//                        )
//                    }
                    // PatientHomeScreen()
                    //ChatbotScreen()

                    //SplashScreen()

//                    OnboardingScreen(
//                            onLoginClick = { },
//                        onSignUpClick = { }
//                    )
//                    SignUpScreen(
//                        onBackClick = { },
//                        onSignUpClick = { },
//                        onLoginClick = { }
//                    )

//                    LoginScreen(
//                        onBackClick = {
//                        },
//                        onSignUpClick = {
//                        },
//                        onLoginClick = {
//                        }
//                    )

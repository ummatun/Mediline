// File: AppNavGraph.kt
package com.example.mediline.presentation.screen.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediline.presentation.screen.auth.AuthViewModel
import com.example.mediline.presentation.screen.auth.LoginScreen
import com.example.mediline.presentation.screen.auth.SignUpScreen
import com.example.mediline.presentation.screen.home.PatientHomeScreen
import com.example.mediline.presentation.screen.chatbot.ChatbotScreen
import com.example.mediline.presentation.screen.profile.ProfileScreen
import com.example.mediline.presentation.screen.doctors.DoctorsListScreen
import com.example.mediline.presentation.screen.doctors.DoctorDetailScreen
import com.example.mediline.presentation.screen.schedule.DoctorScheduleScreen
import com.example.mediline.presentation.screen.profile.EditProfileScreen
import com.example.medilineapp.presentation.screen.onboarding.OnboardingScreen
import com.example.medilineapp.presentation.screen.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.OnboardingScreen.route) {
            OnboardingScreen(
                onLoginClick = { navController.navigate(Screen.LoginScreen.route) },
                onSignUpClick = { navController.navigate(Screen.SignUpStep1Screen.route) }
            )
        }

        composable(Screen.LoginScreen.route) {
            val authViewModel: AuthViewModel = viewModel()
            LoginScreen(
                authViewModel = authViewModel,
                onBackClick = { navController.popBackStack() },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                    }
                },
                onSignUpClick = { navController.navigate(Screen.SignUpStep1Screen.route) }
            )
        }

        composable(Screen.SignUpStep1Screen.route) {
            val authViewModel: AuthViewModel = viewModel()
            SignUpScreen(
                authViewModel = authViewModel,
                onBackClick = { navController.popBackStack() },
                onSignUpSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Screen.LoginScreen.route) }
            )
        }

        composable(Screen.Home.route) {
            PatientHomeScreen(navController = navController)
        }

        composable(Screen.Chatbot.route) {
            ChatbotScreen(
                onBackClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Chatbot.route) { inclusive = true }
                    }
                },
            )
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }

        composable(Screen.DoctorsListScreen.route) {
            DoctorsListScreen(navController = navController)
        }

        composable(Screen.DoctorDetailScreen.route) {
            DoctorDetailScreen(navController = navController)
        }

        composable(Screen.DoctorScheduleScreen.route) {
            DoctorScheduleScreen()
        }

    }
}

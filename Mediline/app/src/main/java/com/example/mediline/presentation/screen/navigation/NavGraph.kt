// File: AppNavGraph.kt
package com.example.mediline.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediline.presentation.screen.auth.LoginScreen
import com.example.mediline.presentation.screen.auth.SignUpScreen
import com.example.mediline.presentation.screen.navigation.Screen

// আপনার সব স্ক্রিন ইম্পোর্ট করুন
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
        composable(Screen.Home.route) {
            PatientHomeScreen(navController = navController)
        }
        composable(Screen.Chatbot.route) {
            ChatbotScreen(
                onBackClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Chatbot.route) { inclusive = true } // Back এ ফিরে আসবে না
                    }
                },
            )
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.DoctorsListScreen.route) {
            DoctorsListScreen(navController = navController)
        }
        composable(Screen.DoctorScheduleScreen.route) {
            DoctorScheduleScreen()
        }
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)

        }
        composable(Screen.DoctorDetailScreen.route) {
            DoctorDetailScreen(navController = navController)

        }
        composable(Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.OnboardingScreen.route) {
            OnboardingScreen(
                onLoginClick = { navController.navigate(Screen.LoginScreen.route) },
                onSignUpClick = { navController.navigate(Screen.SignUpStep1Screen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onLoginClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingScreen.route) { inclusive = true } // Back এ ফিরে আসবে না
                    }
                },
                onSignUpClick = { navController.navigate(Screen.SignUpStep1Screen.route) }
            )
        }
        composable(Screen.SignUpStep1Screen.route) {
            SignUpScreen(
                onBackClick = { navController.popBackStack() },
                onSignUpClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Screen.LoginScreen.route) }
            )
        }




        // নতুন স্ক্রিন লাগলে এখানেই যোগ করবেন
    }
}
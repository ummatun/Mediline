package com.example.mediline.presentation.screen.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chatbot : Screen("chatbot_screen")
    object ProfileScreen : Screen("profile")
    object EditProfileScreen : Screen("edit_profile")

    object DoctorScheduleScreen : Screen("schedule")
    object DoctorsListScreen : Screen("doctor_list")

    // পরে যা লাগবে যোগ করবেন
    object DoctorDetailScreen : Screen("doctor_detail")
    object SplashScreen : Screen("splash")
    object OnboardingScreen : Screen("onboarding")
    object LoginScreen : Screen("login")
    object SignUpStep1Screen : Screen("signup_step1") // ✅ Fixed typo









}



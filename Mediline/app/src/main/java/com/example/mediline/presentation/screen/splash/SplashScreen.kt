package com.example.medilineapp.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediline.R
import com.example.mediline.presentation.screen.navigation.Screen
import com.example.mediline.ui.theme.BlueScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
    ) {
    LaunchedEffect(key1 = true) {
        delay(3000) // 3 seconds
        navController.navigate(Screen.OnboardingScreen.route) {
            popUpTo(Screen.SplashScreen.route) { inclusive = true } // Splash remove
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueScreen)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mediline_logo),
                contentDescription = "MediLine Logo",
                modifier = Modifier.size(140.dp) // যত বড় চাস
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "MediLine",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
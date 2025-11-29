package com.example.medilineapp.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediline.R
import com.example.mediline.ui.theme.BlueScreen
import com.example.mediline.ui.theme.BlueLight

@Composable
fun OnboardingScreen(

    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.onboarding_logo_blue), // তোর নীল লোগো
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "MediLine",
            color = BlueScreen,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Find trusted doctors, book appointments instantly, and check your symptoms – anytime, anywhere.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Log In Button
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BlueScreen),
            shape = MaterialTheme.shapes.large
        ) {
            Text("Log In", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button
        OutlinedButton(
            onClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BlueScreen,
                containerColor = BlueLight.copy(alpha = 0.3f)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Text("Sign Up", fontSize = 18.sp)
        }
    }
}
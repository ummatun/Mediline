package com.example.mediline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mediline.presentation.screen.navigation.AppNavGraph
import com.example.mediline.ui.theme.MedilineTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)  // 🔹 Required
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


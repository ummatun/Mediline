package com.example.mediline.presentation.screen.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SocialButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .size(100.dp, 90.dp)
            .background(Color(0xFFF8FAFF), RoundedCornerShape(20.dp))
            .border(2.dp, Color(0xFFE8F0FE), RoundedCornerShape(20.dp))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Unspecified, modifier = Modifier.size(36.dp))
            Spacer(Modifier.height(8.dp))
            Text(text, color = Color(0xFF666666), fontSize = 12.sp)
        }
    }
}
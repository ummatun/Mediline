// File: AuthTextField.kt
package com.example.mediline.presentation.screen.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mediline.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var showPassword by remember { mutableStateOf(false) }

    // সবচেয়ে সহজ + কোনো ভার্সনে কখনো ভাঙ্গবে না
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFF999999)) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFF), RoundedCornerShape(16.dp))
            .border(2.dp, Color(0xFFE8F0FE), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        visualTransformation = if (isPassword && !showPassword)
            PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_eye),
                        contentDescription = null,
                        tint = Color(0xFF888888)
                    )
                }
            }
        }
    )
}
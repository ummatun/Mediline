package com.example.mediline.presentation.screen.chatbot

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource // <-- 1. IMPORT ADDED
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mediline.R // <-- 2. IMPORT ADDED
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable


// A data class to hold our chat messages
data class ChatMessage(
    val text: String,
    val isFromUser: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(onBackClick: () -> Unit) {
    // CoroutineScope for launching async tasks
    val coroutineScope = rememberCoroutineScope()

    // State for the list of messages
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }
    // State for the user's input text
    var userInput by remember { mutableStateOf("") }
    // State to show a loading indicator
    var isLoading by remember { mutableStateOf(false) }
    // State to scroll the list to the bottom
    val listState = rememberLazyListState()

    // Initialize the GenerativeModel
    val apiKey = stringResource(id = R.string.gemini_api_key)

    // 2. Pass the regular 'apiKey' string into the remember block
    val generativeModel = remember(apiKey) { // Pass apiKey to remember
        GenerativeModel(
            modelName = "models/gemini-2.0-flash",
            apiKey = apiKey, // Use the simple string here
            systemInstruction = content {
                text("You are a helpful medical assistant. Provide concise, safe, and informative advice.")
            }        )

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat With AI", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Chat messages area
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages) { message ->
                    ChatMessageItem(message)
                }
                if (isLoading) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            // Input area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    label = { Text("Ask me anything...") },
                    modifier = Modifier.weight(1f),
                    enabled = !isLoading
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (userInput.isNotBlank() && !isLoading) {
                            // 1. Add user message to the list
                            val userMessage = ChatMessage(userInput, isFromUser = true)
                            messages = messages + userMessage
                            val prompt = userInput
                            userInput = "" // Clear input field

                            // 2. Show loading and call the AI
                            isLoading = true
                            coroutineScope.launch {
                                try {
                                    val response = generativeModel.generateContent(prompt)
                                    val aiMessage = ChatMessage(response.text ?: "Sorry, I couldn't process that.", isFromUser = false)
                                    messages = messages + aiMessage
                                } catch (e: Exception) {
                                    // Handle errors (e.g., no internet, API key issue)
                                    val errorMessage = ChatMessage("Error: ${e.message}", isFromUser = false)
                                    messages = messages + errorMessage
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    },
                    enabled = userInput.isNotBlank() && !isLoading
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send Message")
                }
            }
        }
    }

    // This effect will run whenever the number of messages changes,
    // and it will scroll the list to the last item.
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier.widthIn(max = 300.dp), // Constrain the width of the card
            colors = CardDefaults.cardColors(
                containerColor = if (message.isFromUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                fontWeight = if (message.isFromUser) FontWeight.SemiBold else FontWeight.Normal
            )
        }
    }
}

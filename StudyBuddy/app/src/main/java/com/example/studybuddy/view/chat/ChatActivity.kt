package com.example.studybuddy.view.chat

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studybuddy.domain.model.Message
import com.example.studybuddy.ui.theme.buttonColor
import com.example.studybuddy.ui.theme.grey
import com.example.studybuddy.view.destinations.ChatScreenRouteDestination
import com.example.studybuddy.view.destinations.MainScreenRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Destination
@Composable
fun ChatScreenRoute(
    chatId: String,
    navigator: DestinationsNavigator
) {
    val viewModel: ChatListViewModel = hiltViewModel()
    ChatScreen(
        chatId = chatId,
        onSendMessage = { messageText ->
            viewModel.onSendMessage(chatId, messageText)
        },
        onBackClick = { navigator.navigate(MainScreenRouteDestination(1)) {
            popUpTo(ChatScreenRouteDestination) { inclusive = true }
            viewModel.depopulateMessage()
        }}
    )
}

@Composable
fun ChatScreen(
    chatId: String,
    onSendMessage: (String) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val vm: ChatListViewModel = hiltViewModel()
    val myUser = vm.userData.value
    val currentChat = vm.chats.value.firstOrNull { it.chatId == chatId }
    val chatUser = if (myUser?.userId == currentChat?.user1?.userId) currentChat?.user2 else currentChat?.user1
    val messages = vm.chatMessage.value

    // Group messages by date
    val groupedMessages = messages.groupBy { getDateFromDateTime(it.time) }

    LaunchedEffect(key1 = chatId) {
        vm.getMessages(chatId)
    }

    BackHandler {
        vm.depopulateMessage()
        onBackClick()
    }

    Scaffold(
        topBar = {
            ChatScreenTopBar(
                chatPartnerName = chatUser?.name ?: "",
                onBackClick = onBackClick
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding() // Adjust the entire column when the keyboard appears
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                groupedMessages.toList().reversed().forEach { (date, messagesForDate) ->
                    item {
                        DateHeader(date)
                    }

                    items(messagesForDate) { message ->
                        MessageItem(
                            message = message,
                            isUserMe = message.userId == myUser?.userId,
                            time = convertTimeTo12HourFormat(message.time)
                        )
                    }
                }
            }
            MessageInput(
                onSendMessage = { text ->
                    onSendMessage(text)
                },
                modifier = Modifier
                    .navigationBarsPadding()  // Handle navigation bar padding
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatScreenTopBar(
    chatPartnerName: String,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Navigate Back")
            }
        },
        title = { Text(text = chatPartnerName) }
    )
}

@Composable
fun MessageItem(message: Message, isUserMe: Boolean, time: String) {
    Row(
        horizontalArrangement = if (isUserMe) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isUserMe) buttonColor else Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
                .widthIn(max = 250.dp)
        ) {
            Column {
                // Message text
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.W500),
                    color = if (isUserMe) Color.White else Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)  // Space between message and time
                )


                // Time text aligned to the bottom end of the box
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    color = if (isUserMe) Color.White.copy(alpha = 0.7f) else Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(onSendMessage: (String) -> Unit, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Type a message") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = grey,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onSendMessage(text)
                        text = ""
                    }
                }) {
                    Icon(Icons.Default.Send, contentDescription = "Send", tint = buttonColor, modifier = Modifier.size(32.dp))
                }
            }
        )
    }
}

@Composable
fun DateHeader(date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier
                .background(grey, shape = RoundedCornerShape(16.dp))
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .wrapContentSize()
        )
    }
}


// Function to extract just the date (e.g., "Aug 15, 2024") from the dateTime string
fun getDateFromDateTime(dateTime: String): String {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    return try {
        val date: Date = inputFormat.parse(dateTime) ?: return ""
        outputFormat.format(date)
    } catch (e: Exception) {
        Log.e("DateConversion", "Error parsing date: ${e.message}")
        ""
    }
}

// Function to convert time from 24-hour format to 12-hour format (e.g., "10:30 AM")
fun convertTimeTo12HourFormat(dateTime: String): String {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    return try {
        val date: Date = inputFormat.parse(dateTime) ?: return ""
        outputFormat.format(date)
    } catch (e: Exception) {
        Log.e("DateConversion", "Error parsing time: ${e.message}")
        ""
    }
}


// TODO: if possible fix the message issue message goes up on keyboard open


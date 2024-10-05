package com.example.studybuddy.view.chat

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studybuddy.R
import com.example.studybuddy.domain.model.ChatData
import com.example.studybuddy.domain.model.UserData
import com.example.studybuddy.ui.theme.buttonColor
import com.example.studybuddy.ui.theme.grey
import com.example.studybuddy.view.components.CircularProgressBar
import com.example.studybuddy.view.components.MembershipPurchaseScreen
import com.example.studybuddy.view.destinations.ChatScreenRouteDestination
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.random.Random


@Destination(
    deepLinks = [
    DeepLink(action = Intent.ACTION_VIEW, uriPattern = "study_buddy://chat_list")
]
)


@Composable
fun ChatListScreen(navigator: DestinationsNavigator) {
    val vm: ChatListViewModel = hiltViewModel()
    var isSubscribed by remember { mutableStateOf(true) }
    val inProgress = vm.isLoading
    val showDialog = remember { mutableStateOf(false) }
    val context: Context = LocalContext.current
    val userData = vm.userData.value

    Log.d("ChatListScreen", "userData: $userData")
    Log.d("ChatListScreen", "current time: ${System.currentTimeMillis()}")

    LaunchedEffect(Unit) {
        userData?.let {
            if (it.PremiumMember == true && it.expiry!! > System.currentTimeMillis()) {
                isSubscribed = true
                Log.d("ChatListScreen", "User is subscribed: $userData")
            } else {
                isSubscribed = false
                Log.d("ChatListScreen", "User is not subscribed or subscription expired: $userData")
            }
        }
    }

    if (inProgress.value) {
        CircularProgressBar()
    } else {
        ChatScreenContent(
            isSubscribed = true,//isSubscribed,
            chats = vm.chats.value,
            showDialog = showDialog.value,
            onFabClick = { showDialog.value = true },
            onDismiss = { showDialog.value = false },
            onAddChat = {
                vm.onAddChat(it)
                showDialog.value = false
                Toast.makeText(context, "Connection made request", Toast.LENGTH_SHORT).show()
            },
            context = context,
            userData = userData!!,
            navigator = navigator  // Pass navigator to ChatScreenContent
        )
    }
}


@Composable
fun ChatScreenContent(
    isSubscribed: Boolean,
    chats: List<ChatData>,
    showDialog: Boolean,
    onFabClick: () -> Unit,
    onDismiss: () -> Unit,
    onAddChat: (String) -> Unit,
    context: Context,
    userData: UserData,
    navigator: DestinationsNavigator // Add navigator parameter
) {
    if (isSubscribed) {
        Scaffold(
            topBar = { ChatTopBar() },
            floatingActionButton = {
                FAB(
                    showDialog = showDialog,
                    onFabClick = onFabClick,
                    onDismiss = onDismiss,
                    onAddChat = onAddChat,
                    context = context,
                    modifier = Modifier.offset(y = (-70.dp))
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                if (chats.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "No Chats Available")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        items(chats){
                            chat->
                            val chatUser =
                                if (chat.user1.userId ==userData.userId )
                            {
                                chat.user2
                            }
                            else{
                                chat.user1
                            }

                            CommonRow(
                                name = chatUser.name,
                                navigator = navigator,  // Pass navigator
                                chatId = chat.chatId,   // Pass chatId
                                onItemClicked = {

                                }
                            )

                        }
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MembershipPurchaseScreen()
        }
    }
}

@Composable
fun CommonRow(
    name: String?,
    navigator: DestinationsNavigator,
    chatId: String?,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {
                onItemClicked.invoke()
                if (chatId != null) {
                    navigator.navigate(ChatScreenRouteDestination(chatId))

                }
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val images = listOf(
                R.drawable.user1,
                R.drawable.user2,
                R.drawable.user4,
                R.drawable.user5,
                R.drawable.user3,
                R.drawable.user6,
                R.drawable.user7,
                R.drawable.user8,
                R.drawable.user
            )

            // Select a random image
            val randomImage = images[Random.nextInt(images.size)]

            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(42.dp)
                    .clip(CircleShape),
                painter = painterResource(id = randomImage),
                contentDescription = "User Image"
            )
            Spacer(modifier = Modifier.width(70.dp))

            Text(
                text = name ?: ".....",
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Chats",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAB(
    showDialog: Boolean,
    onFabClick: () -> Unit,
    onDismiss: () -> Unit,
    onAddChat: (String) -> Unit,
    context: Context,
    modifier: Modifier
) {
    val addMember = remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss.invoke()
                addMember.value = ""
            },
            confirmButton = {
                Button(
                    onClick = {if (addMember.value.isEmpty() or !addMember.value.isDigitsOnly())
                    {
                        Toast.makeText(context, "Invalid Number", Toast.LENGTH_SHORT).show()
                    }
                        else{
                           onAddChat(addMember.value)
                        addMember.value = ""
                    }

                              },
                    colors = ButtonDefaults.buttonColors(buttonColor),
                ) {
                    Text(text = "Add Chat")
                }
            },
            title = { Text(text = "Add Chat") },
            text = {
                OutlinedTextField(
                    value = addMember.value,
                    onValueChange = { addMember.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = grey,
                        unfocusedIndicatorColor = grey.copy(alpha = 0.3f),
                        focusedIndicatorColor = grey.copy(alpha = 0.3f)
                    ),
                    singleLine = true
                )
            }
        )
    } else {
        FloatingActionButton(
            onClick = { onFabClick.invoke() },
            containerColor = buttonColor,
            shape = CircleShape,
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add Chat",
                tint = Color.White
            )
        }
    }
}



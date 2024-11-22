package com.example.studybuddy.view.FocusMode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studybuddy.R
import com.example.studybuddy.ui.theme.grey
import com.example.studybuddy.view.components.CustomToggleButton


@Composable
fun FocusScreen() {
    val (blockYoutube, setBlockYoutube) = remember { mutableStateOf(false) }
    val (blockInstagram, setBlockInstagram) = remember { mutableStateOf(false) }
    val (blockWhatsapp, setBlockWhatsapp) = remember { mutableStateOf(false) }

    var showConfirmDialog by remember { mutableStateOf<Pair<String, () -> Unit>?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { FocusTopBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Do you want to block any social media app?",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500)
            )
            AppBlockCard(
                appName = "YouTube",
                isBlocked = blockYoutube,
                onToggle = {
                    if (!blockYoutube) { // Show confirmation dialog only when toggling on
                        showConfirmDialog = "YouTube" to { setBlockYoutube(!blockYoutube) }
                    } else {
                        setBlockYoutube(!blockYoutube) // Directly toggle off
                    }
                }
            )
            AppBlockCard(
                appName = "Instagram",
                isBlocked = blockInstagram,
                onToggle = {
                    if (!blockInstagram) {
                        showConfirmDialog = "Instagram" to { setBlockInstagram(!blockInstagram) }
                    } else {
                        setBlockInstagram(!blockInstagram)
                    }
                }
            )
            AppBlockCard(
                appName = "WhatsApp",
                isBlocked = blockWhatsapp,
                onToggle = {
                    if (!blockWhatsapp) {
                        showConfirmDialog = "WhatsApp" to { setBlockWhatsapp(!blockWhatsapp) }
                    } else {
                        setBlockWhatsapp(!blockWhatsapp)
                    }
                }
            )
        }

        showConfirmDialog?.let { (appName, onConfirm) ->
            ConfirmBlockDialog(
                appName = appName,
                onConfirm = {
                    onConfirm()
                    showConfirmDialog = null
                },
                onDismiss = { showConfirmDialog = null }
            )
        }
    }
}


@Composable
fun AppBlockCard(
    appName: String,
    isBlocked: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(grey),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = when (appName) {
                    "YouTube" -> painterResource(R.drawable.youtube)
                    "Instagram" -> painterResource(R.drawable.instagram)
                    "WhatsApp" -> painterResource(R.drawable.whatsapp)
                    else -> painterResource(R.drawable.youtube)//todo change this
                },
                contentDescription = "Focus",
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = appName,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            CustomToggleButton(
                checked = isBlocked,
                onCheckedClick = { onToggle() }
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Focus Mode",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
fun ConfirmBlockDialog(
    appName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Block $appName?") },
        text = { Text(text = "Are you sure you want to block $appName?") },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



@Preview
@Composable
fun FocusScreenPreview(){
    FocusScreen()
}
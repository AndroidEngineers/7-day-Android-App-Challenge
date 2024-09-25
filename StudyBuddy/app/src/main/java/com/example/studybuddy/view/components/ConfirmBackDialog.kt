package com.example.studybuddy.view.components


import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ConfirmBackDialog(onConfirmBack: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    // Intercept back button press and show dialog
    BackHandler(enabled = true) {
        showDialog = true
    }

    // Show confirmation dialog when needed
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user touches outside the dialog or presses the back button
                showDialog = false
            },
            title = { Text("Confirm Exit") },
            text = { Text("Are you sure you want to leave?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onConfirmBack() // Handle the confirmed back action
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false // Simply close the dialog
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}
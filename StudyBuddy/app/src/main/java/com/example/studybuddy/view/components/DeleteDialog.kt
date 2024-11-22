package com.example.studybuddy.view.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    isOpen: Boolean,
    title:String,
    bodyText:String,
    onDismiss: () -> Unit,
    onConfirmBtnClick: () -> Unit
){


    if (isOpen){
        AlertDialog(
            title = { Text(text=title) },
            onDismissRequest = onDismiss,
            text = {
                   Text(text = bodyText)
            },
            confirmButton = {
                TextButton(onClick = onConfirmBtnClick){ Text(text = "Delete")}

            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }

            }

        )
    }
}
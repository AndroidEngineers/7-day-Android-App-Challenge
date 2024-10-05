package com.example.studybuddy.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDatePicker(
    state: DatePickerState,
    isOpen: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    confirmButtonText: String = "OK",
    dismissButtonText: String = "Cancel"
)
{
    if (isOpen) {

        DatePickerDialog(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                TextButton(onClick = onConfirmRequest) {
                    Text(text = confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = dismissButtonText)
                }
            },
            content = {
                androidx.compose.material3.DatePicker(
                    state = state,
                    dateValidator = {
                        timestamp->
                        val selectedDate = Instant.ofEpochMilli(timestamp)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()

                        val currentDate = LocalDate.now(ZoneId.systemDefault())
                        selectedDate >= currentDate
                    })
            }
        )
    }
}
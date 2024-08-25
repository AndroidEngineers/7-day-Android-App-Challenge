package com.example.taskninja20.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.taskninja20.R
import com.example.taskninja20.reusable.CustomTextBold
import java.util.*

@Composable
fun AddTaskScreen() {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf(TextFieldValue("")) }
    var selectedDate by remember { mutableStateOf("Select Deadline Date") }
    var taskStatus by remember { mutableStateOf("In-Progress") }
    var taskPriority by remember { mutableStateOf("Low") }
    var showDatePicker by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row() {
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .weight(.05f)
            )
            Box( modifier = Modifier.weight(.9f)){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    // Task Title
                    Spacer(modifier = Modifier.height(16.dp))

                    CustomTextBold(
                        text = stringResource(id = R.string.add_task),
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        label = { Text(stringResource(id = R.string.enter_title)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Select Deadline Date
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = { selectedDate = it },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Icon(Icons.Default.DateRange, contentDescription = stringResource(id = R.string.select_date))
                            }
                        }
                    )

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDateSelected = { date ->
                                selectedDate = date.toString() // Format this as needed
                                showDatePicker = false
                            },
                            onDismissRequest = { showDatePicker = false }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Task Status (Dropdown)
                    DropdownMenuField(
                        label = stringResource(id = R.string.task_status),
                        options = listOf(stringResource(id = R.string.in_progress), stringResource(
                            id = R.string.pending
                        ), stringResource(
                            id = R.string.cancelled
                        ), stringResource(
                            id = R.string.completed
                        )),
                        selectedOption = taskStatus,
                        onOptionSelected = { taskStatus = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Task Priority (Dropdown)
                    DropdownMenuField(
                        label = stringResource(id = R.string.task_priority),
                        options = listOf(stringResource(
                            id = R.string.high
                        ), stringResource(
                            id = R.string.medium
                        ), stringResource(id = R.string.low)),
                        selectedOption = taskPriority,
                        onOptionSelected = { taskPriority = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Task Description
                    OutlinedTextField(
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        placeholder = { Text(stringResource(id = R.string.enter_description)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )

                    Spacer(modifier = Modifier.padding(bottom = 50.dp))

                    // Add Task Button
                    AddTaskButton(modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth())

                }
            }

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .weight(.05f)
            )
        }
    }

}

@Composable
fun AddTaskButton(modifier: Modifier){
    Button(
        onClick = { /* Handle Add Task Action */ },
        modifier = modifier
    ) {
        Text(stringResource(id = R.string.add_task), fontSize = 18.sp)
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Option")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }, text = { Text(option) })
            }
        }
    }
}

@Composable
fun DatePickerDialog(
    onDateSelected: (Date) -> Unit,
    onDismissRequest: () -> Unit
) {
    // Implementation of date picker dialog using Material Date Picker or any custom implementation
    // Placeholder function
    onDateSelected(Date())
    onDismissRequest()
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen()
}

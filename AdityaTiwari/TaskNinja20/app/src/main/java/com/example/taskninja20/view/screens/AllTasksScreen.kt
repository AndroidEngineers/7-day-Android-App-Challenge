package com.example.taskninja20.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskninja20.R
import com.example.taskninja20.model.Task
import com.example.taskninja20.reusable.CustomTextBold
import com.example.taskninja20.reusable.CustomTextMedium

@Composable
fun AllTasksScreen() {
    val tasks = getDummyTaskList()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column() {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            CustomTextBold(
                text = stringResource(id = R.string.all_task),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            //here implement the lazy column for vertical list for tasks

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(tasks.size) {
                    TaskCard(task = tasks[it])
                }
            }
        }
    }
}



@Composable
fun TaskCard(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp,end = 16.dp,top = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD08A) // Set the background color here
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
                // Task Status Icon
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    // Task Title
                    Text(
                        text = task.taskTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 8.dp, end = 40.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Task Description
                    Text(
                        text = task.taskDescription,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Task Category
                    CustomTextMedium(
                        text = task.taskCategory,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 18.dp,bottom = 8.dp)
                    )
                }

//            Box(
//                modifier = Modifier
//                    .width(50.dp)
//                    .height(25.dp)
//                    .padding(end = 16.dp)
//                    .background(
//                        color = when (task.priority) {
//                            1 -> Color(0xFF4CAF50) // Low - Green
//                            2 -> Color(0xFFFFC107) // Medium - Yellow
//                            3 -> Color(0xFFF44336) // High - Red
//                            else -> Color.Gray
//                        },
//                        shape = RoundedCornerShape(9.dp)
//                    )
//            )

        }
    }
}

fun getDummyTaskList(): List<Task> {
    return listOf(
        Task(
            taskTitle = "Implement Authentication",
            taskDescription = "Integrate OAuth 2.0 for user authentication.",
            taskStatus = 1,
            priority = 2,
            taskCategory = "Work"
        ),
        Task(
            taskTitle = "Design Home Screen",
            taskDescription = "Create a new home screen layout.",
            taskStatus = 2,
            priority = 3,
            taskCategory = "Personal"
        ),
        Task(
            taskTitle = "Fix Bugs in Payment Module",
            taskDescription = "Resolve the issue with payment processing.",
            taskStatus = 3,
            priority = 1,
            taskCategory = "Urgent"
        ),
        Task(
            taskTitle = "Write Documentation",
            taskDescription = "Update the project documentation.",
            taskStatus = 4,
            priority = 1,
            taskCategory = "Work"
        ), Task(
            taskTitle = "Implement Authentication",
            taskDescription = "Integrate OAuth 2.0 for user authentication.",
            taskStatus = 1,
            priority = 2,
            taskCategory = "Work"
        ),
        Task(
            taskTitle = "Design Home Screen",
            taskDescription = "Create a new home screen layout.",
            taskStatus = 2,
            priority = 3,
            taskCategory = "Personal"
        ),
        Task(
            taskTitle = "Fix Bugs in Payment Module",
            taskDescription = "Resolve the issue with payment processing.",
            taskStatus = 3,
            priority = 1,
            taskCategory = "Urgent"
        ),
        Task(
            taskTitle = "Write Documentation",
            taskDescription = "Update the project documentation.",
            taskStatus = 4,
            priority = 1,
            taskCategory = "Work"
        ), Task(
            taskTitle = "Implement Authentication",
            taskDescription = "Integrate OAuth 2.0 for user authentication.",
            taskStatus = 1,
            priority = 2,
            taskCategory = "Work"
        ),
        Task(
            taskTitle = "Design Home Screen",
            taskDescription = "Create a new home screen layout.",
            taskStatus = 2,
            priority = 3,
            taskCategory = "Personal"
        ),
        Task(
            taskTitle = "Fix Bugs in Payment Module",
            taskDescription = "Resolve the issue with payment processing.",
            taskStatus = 3,
            priority = 1,
            taskCategory = "Urgent"
        ),
        Task(
            taskTitle = "Write Documentation",
            taskDescription = "Update the project documentation.",
            taskStatus = 4,
            priority = 1,
            taskCategory = "Work"
        )
    )
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun previewTask() {
    TaskCard(
        Task(
        taskTitle = "Write Documentation",
        taskDescription = "Update the project documentation. This is really big description for any task because we don't what should we use if ",
        taskStatus = 4,
        priority = 1,
        taskCategory = "Work"
    )
    )
}
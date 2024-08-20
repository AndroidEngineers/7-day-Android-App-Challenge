package com.example.taskninja20.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.taskninja20.reusable.CustomTextBold
import com.example.taskninja20.ui.theme.TaskNinja20Theme

class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskNinja20Theme {
                addTaskScreen()
            }
        }
    }
}

@Composable
fun addTaskScreen() {
    CustomTextBold(text = "Add Task", fontSize = 24.sp, color = Color.Black)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    TaskNinja20Theme {
        addTaskScreen()
    }
}
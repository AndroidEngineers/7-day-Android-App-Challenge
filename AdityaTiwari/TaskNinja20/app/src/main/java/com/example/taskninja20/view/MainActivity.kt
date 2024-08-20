package com.example.taskninja20.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskninja20.ui.theme.TaskNinja20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskNinja20Theme {
                var selectedTab by remember { mutableStateOf("Dashboard") }
                Scaffold(bottomBar = {

                },modifier = Modifier.fillMaxSize()
                ){ innerPadding ->
                    when (selectedTab) {
                        "Dashboard" -> DashboardScreen(modifier = Modifier.padding(innerPadding))
                        "All Tasks" -> AllTasksScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskNinja20Theme {
    }
}
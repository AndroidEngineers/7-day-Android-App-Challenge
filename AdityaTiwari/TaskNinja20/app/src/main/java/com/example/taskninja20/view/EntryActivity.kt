package com.example.taskninja20.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import com.example.taskninja20.ui.theme.TaskNinja20Theme
import com.example.taskninja20.view.screens.AllTasksScreen


class EntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskNinja20Theme {
                AllTasksScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TaskNinja20Theme {

    }
}
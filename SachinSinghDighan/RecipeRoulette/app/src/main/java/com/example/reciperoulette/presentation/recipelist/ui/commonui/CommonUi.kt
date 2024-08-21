package com.example.make_food.ui.commonui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(data: String) {
    TopAppBar(title = { Text(text = data) })
}
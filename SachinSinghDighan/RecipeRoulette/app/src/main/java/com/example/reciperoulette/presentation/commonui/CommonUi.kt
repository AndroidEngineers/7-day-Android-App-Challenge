package com.example.make_food.ui.commonui

import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme


@Composable
fun AppBar(data: String) {
    TopAppBar(
        backgroundColor = Color.White,
        contentColor = Color.White,
        elevation = 8.dp,
    ) {
        Text(text = data)
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    RecipeRouletteTheme {
        AppBar("Recipe")
    }
}
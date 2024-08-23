package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.make_food.ui.commonui.AppBar
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun RecipeDetailScreen(navController: NavHostController) {
    RecipeRouletteTheme {
        Scaffold(
            topBar = { AppBar(stringResource(id = R.string.recipe_detail)) },
            content = { innerPadding ->
                RecipeDetail(
                    name = "Android",
                    innerPadding,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeRouletteTheme {
        RecipeDetailScreen(navController = rememberNavController())
    }
}
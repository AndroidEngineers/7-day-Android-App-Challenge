package com.example.reciperoulette.presentation.navigation

sealed class Screen(val route: String) {
    object RecipeList : Screen("recipe_list_screen")
    object RecipeDetail : Screen("recipe_detail_ screen")
}
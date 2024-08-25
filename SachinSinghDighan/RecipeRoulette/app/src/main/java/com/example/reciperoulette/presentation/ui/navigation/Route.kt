package com.example.reciperoulette.presentation.ui.navigation

sealed class Route(val route: String) {
    object RecipeListScreen : Route("recipe-list-screen")
    object RecipeDetailScreen : Route("recipe-detail-Route")
}
package com.example.reciperoulette.screens.recipedetailscree

data class RecipeDetailState(
    var summary: String? = null,
    var instruction: String? = null,
    var gradiant: List<String> = emptyList()
)
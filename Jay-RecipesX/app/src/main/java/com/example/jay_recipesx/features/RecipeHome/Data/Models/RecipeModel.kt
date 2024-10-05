package com.example.jay_recipesx.features.RecipeHome.Data.Models

data class RecipeModel(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: String,
    val cookingTime: Int,
    val servings: Int,
    val isVegetarian: Boolean,
    val imageUrl: String
)

package com.example.jay_recipesx.features.RecipeHome.Data.Models

data class Equipment(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String,
    val temperature: Temperature
)
package com.example.jay_recipesx.features.RecipeHome.Data.Models

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)
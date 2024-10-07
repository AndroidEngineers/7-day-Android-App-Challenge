package com.example.reciperoulette.screens.recipedetailscree

import com.example.reciperoulette.model.ExtendedIngredient

data class RecipeDetailState(
    var summary: String? = null,
    var instruction: String? = null,
    var gradiant: List<ExtendedIngredient> = emptyList(),
    var recipeName:String? = null
)
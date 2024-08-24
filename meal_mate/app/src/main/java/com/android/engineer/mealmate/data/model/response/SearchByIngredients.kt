package com.android.engineer.mealmate.data.model.response

data class SearchByIngredients(
    val id: Int,
    val image: String,
    val title: String,
    val likes: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedUnUsedIngredients>,
    val usedIngredientCount: Int,
    val usedIngredients: List<MissedUnUsedIngredients>,
    val unusedIngredients: List<MissedUnUsedIngredients>,
    val spoonacularSourceUrl: String
)

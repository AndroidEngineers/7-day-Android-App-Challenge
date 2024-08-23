package com.android.engineer.mealmate.model.data.response

data class SearchByNutrients(
    val id: Int,
    val title: String,
    val image: String,
    val calories: Int,
    val carbs: String,
    val fat: String,
    val protein: String,
    val spoonacularSourceUrl: String
)
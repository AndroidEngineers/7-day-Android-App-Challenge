package com.example.reciperoulette.domain.model

import com.google.gson.annotations.SerializedName

data class RecipesContent(
    @SerializedName("recipes")
    val recipes: List<Recipe> = listOf()
)

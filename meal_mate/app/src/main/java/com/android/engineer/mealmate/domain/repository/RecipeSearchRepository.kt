package com.android.engineer.mealmate.domain.repository

interface RecipeSearchRepository {

    suspend fun searchByNutrients()

    suspend fun searchByIngredients()

    suspend fun getRecipeInformationById(recipeId: Int)
}
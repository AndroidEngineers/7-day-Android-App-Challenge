package com.android.engineer.mealmate.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MealAPI {

    @GET("recipes/findByNutrients")
    suspend fun searchByNutrients()
    @GET("recipes/findByIngredients")
    suspend fun searchByIngredients()

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformationById(@Path("id") recipeId: Int)
}
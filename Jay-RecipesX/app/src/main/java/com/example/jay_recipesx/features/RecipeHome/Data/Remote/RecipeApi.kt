package com.example.jay_recipesx.features.RecipeHome.Data.Remote

import com.example.jay_recipesx.BuildConfig
import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RecipeApi {
    @Headers(
        "Content-Type: application/json",
        "x-api-key: ${BuildConfig.API_KEY}"
    )
    @GET("/recipes/random")
    suspend fun getRandomRecipe() : RecipeModel
}
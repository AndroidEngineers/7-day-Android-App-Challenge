package com.example.reciperoulette.data.Network.api

import com.example.reciperoulette.model.Recipe
import com.example.reciperoulette.model.RecipeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/random")
    suspend fun getRecipe(
        @Query("number") number: Int = 10,
        @Query("apiKey") apiKey: String = "17f71ccb2a5246caa26f52dd2344172b"
    ): Response<RecipeData>
}
package com.example.reciperoulette.data.network

import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.model.RecipesContent
import com.example.reciperoulette.presentation.util.AppConstant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("/recipes/random")
    suspend fun getRandomRecipe(@Query("number") number: Int, @Query("apiKey") apiKey:String = AppConstant.API_KEY): RecipesContent

    @GET("/recipes/{id}/information")
    suspend fun getRecipeById(@Path("id") id: Int,@Query("apiKey") apiKey: String): Recipe

}
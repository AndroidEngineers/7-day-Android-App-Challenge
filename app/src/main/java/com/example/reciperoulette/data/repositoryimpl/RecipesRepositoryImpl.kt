package com.example.reciperoulette.data.repositoryimpl

import android.util.Log
import com.example.reciperoulette.data.Network.api.RecipeApi
import com.example.reciperoulette.domain.repository.RecipesRepository
import com.example.reciperoulette.model.Recipe
import com.example.reciperoulette.model.RecipeData
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val recipeApi: RecipeApi) {
     suspend fun getRecipesList(): RecipeData? {
        val recipeListResponse = recipeApi.getRecipe()
        if (recipeListResponse.isSuccessful) {
            Log.d("RecipesRepositoryImpl", "#ak inside getRecipesList: ${recipeListResponse.body()}")
            return recipeListResponse.body()!!
        }
        return null
    }
}
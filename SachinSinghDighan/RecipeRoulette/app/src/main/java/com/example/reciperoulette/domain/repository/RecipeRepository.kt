package com.example.reciperoulette.domain.repository

import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.model.RecipesContent
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipeFromApi(number: Int, apiKey: String): Flow<RecipesContent>

    //fun getRecipeFromDb(): Flow<Recipe>
}
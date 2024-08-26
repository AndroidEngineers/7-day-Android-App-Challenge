package com.example.reciperoulette.domain.repository

import com.example.reciperoulette.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeDetailRepository {
    fun getRecipeById(recipeId: Int, apiKey: String): Flow<Recipe>

}
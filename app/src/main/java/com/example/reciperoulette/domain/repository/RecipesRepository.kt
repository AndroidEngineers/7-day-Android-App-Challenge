package com.example.reciperoulette.domain.repository

import com.example.reciperoulette.model.Recipe
import com.example.reciperoulette.model.RecipeData

interface RecipesRepository {
    suspend fun getRecipesList(): RecipeData?
}
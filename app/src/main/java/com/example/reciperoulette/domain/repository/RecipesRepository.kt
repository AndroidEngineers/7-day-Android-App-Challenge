package com.example.reciperoulette.domain.repository

import com.example.reciperoulette.model.Recipe

interface RecipesRepository {
    suspend fun getRecipesList(): List<Recipe>
}
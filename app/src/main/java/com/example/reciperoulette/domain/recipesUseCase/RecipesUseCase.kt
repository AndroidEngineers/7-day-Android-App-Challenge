package com.example.reciperoulette.domain.recipesUseCase

import com.example.reciperoulette.data.repositoryimpl.RecipesRepositoryImpl
import com.example.reciperoulette.domain.repository.RecipesRepository
import com.example.reciperoulette.model.Recipe
import javax.inject.Inject

class RecipesUseCase @Inject constructor(private val repositoryImpl: RecipesRepository) {
    lateinit var recipeData: List<Recipe>

    suspend fun getRecipeData(): List<Recipe> {
        recipeData = repositoryImpl.getRecipesList()?.recipes ?: emptyList()
        return recipeData
    }

    fun getVegData():List<Recipe>{
        return recipeData.filter { it.vegetarian == true }
    }

    fun getNonVegData():List<Recipe>{
        return recipeData.filter { it.vegetarian == false }
    }
}
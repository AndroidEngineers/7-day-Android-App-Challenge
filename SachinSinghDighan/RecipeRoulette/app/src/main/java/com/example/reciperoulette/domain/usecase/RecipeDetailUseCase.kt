package com.example.reciperoulette.domain.usecase

import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.repository.RecipeDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailUseCase @Inject constructor(private val recipeDetailRepository: RecipeDetailRepository) {
    operator fun invoke(recipeId: Int, apiKey: String): Flow<Recipe> {
        return recipeDetailRepository.getRecipeById(recipeId, apiKey)
    }
}
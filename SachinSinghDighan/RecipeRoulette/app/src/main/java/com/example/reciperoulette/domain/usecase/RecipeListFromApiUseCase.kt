package com.example.reciperoulette.domain.usecase

import com.example.reciperoulette.domain.model.RecipesContent
import com.example.reciperoulette.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeListFromApiUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {

    operator fun invoke(number: Int, apiKey: String): Flow<RecipesContent> {
        return recipeRepository.getRecipeFromApi(number, apiKey)
    }

}
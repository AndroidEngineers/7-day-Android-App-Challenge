package com.example.reciperoulette.di.repositoryimpl

import com.example.reciperoulette.data.network.NetworkService
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.model.RecipesContent
import com.example.reciperoulette.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val networkService: NetworkService): RecipeRepository {
    override fun getRecipeFromApi(number: Int, apiKey: String): Flow<RecipesContent> {
        return flow {
            emit(networkService.getRandomRecipe(number, apiKey))
        }.map {
          it
        }
    }
}
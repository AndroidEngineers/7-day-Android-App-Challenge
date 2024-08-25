package com.example.reciperoulette.di.repositoryimpl

import com.example.reciperoulette.data.network.NetworkService
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.repository.RecipeDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeDetailRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    RecipeDetailRepository {
    override fun getRecipeById(recipeId: Int, apiKey: String): Flow<Recipe> {
        return flow {
            emit(networkService.getRecipeById(recipeId, apiKey))
        }.map {
            it
        }
    }
}
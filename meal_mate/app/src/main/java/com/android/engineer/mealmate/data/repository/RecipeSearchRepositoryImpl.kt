package com.android.engineer.mealmate.data.repository

import com.android.engineer.mealmate.data.remote.MealAPI
import com.android.engineer.mealmate.data.remote.model.response.IngredientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.NutrientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.RecipeInfoByIdResponse
import com.android.engineer.mealmate.data.utils.API_KEY_VALUE
import com.android.engineer.mealmate.repository.remote.RecipeSearchRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeSearchRepositoryImpl @Inject constructor(
    private val api: MealAPI
): RecipeSearchRepository {

    override suspend fun searchByNutrients(filterByNutrients: HashMap<String, String>): Flow<List<NutrientsResponseItem>> {
        return flow {
            val response = api.searchByNutrients(filterByNutrients = filterByNutrients)
            emit(response)
            delay(3000L)
        }.map {
            it
        }
    }

    override suspend fun searchByIngredients(filterByIngredients: HashMap<String, String>): Flow<List<IngredientsResponseItem>> {
        return flow {
            val response = api.searchByIngredients(filterByIngredients = filterByIngredients)
            emit(response)
            delay(3000L)
        }.map {
            it
        }
    }

    override suspend fun getRecipeInformationById(recipeId: Int) : Flow<RecipeInfoByIdResponse> {
        return flow {
            val response = api.getRecipeInformationById(recipeId = recipeId, apiKey = API_KEY_VALUE)
            emit(response)
            delay(3000L)
        }.map {
            it
        }
    }
}
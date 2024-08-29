package com.android.engineer.mealmate.repository.remote

import com.android.engineer.mealmate.data.remote.model.response.IngredientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.NutrientsResponseItem
import com.android.engineer.mealmate.data.remote.model.response.RecipeInfoByIdResponse
import kotlinx.coroutines.flow.Flow

interface RecipeSearchRepository {

    suspend fun searchByNutrients(filterByNutrients: HashMap<String, String>): Flow<List<NutrientsResponseItem>>

    suspend fun searchByIngredients(filterByIngredients: HashMap<String, String>): Flow<List<IngredientsResponseItem>>

    suspend fun getRecipeInformationById(recipeId: Int): Flow<RecipeInfoByIdResponse>
}
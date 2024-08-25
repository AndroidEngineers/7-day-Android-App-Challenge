package com.android.engineer.mealmate.data.repository

import android.app.Application
import com.android.engineer.mealmate.data.remote.MealAPI
import com.android.engineer.mealmate.domain.repository.RecipeSearchRepository
import javax.inject.Inject

class RecipeSearchRepositoryImpl @Inject constructor(
    private val api: MealAPI,
    private val appContext: Application
): RecipeSearchRepository {

    override suspend fun searchByNutrients() {

    }

    override suspend fun searchByIngredients() {

    }

    override suspend fun getRecipeInformationById(recipeId: Int) {
    }
}
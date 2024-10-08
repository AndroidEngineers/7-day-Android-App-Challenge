package com.example.jay_recipesx.features.RecipeHome.Data.Repositories

import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel
import com.example.jay_recipesx.features.RecipeHome.Data.Remote.RecipeApi
import com.example.jay_recipesx.features.RecipeHome.Domain.Repositories.IRecipeRepo
import retrofit2.Call
import javax.inject.Inject
import kotlin.random.Random

class RecipeRepoImpl @Inject constructor(
    private val recipeApi: RecipeApi
) : IRecipeRepo {

    override suspend fun getRandomRecipe(): RecipeModel {
        return recipeApi.getRandomRecipe()
    }

    override suspend fun getSimilarRecipes(id: String) : List<RecipeModel> {
        return listOf();
    }
}
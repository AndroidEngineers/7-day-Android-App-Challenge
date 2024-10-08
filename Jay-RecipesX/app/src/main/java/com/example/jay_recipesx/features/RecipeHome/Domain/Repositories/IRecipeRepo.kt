package com.example.jay_recipesx.features.RecipeHome.Domain.Repositories

import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel

interface  IRecipeRepo  {
   suspend fun getRandomRecipe(): RecipeModel
    suspend fun getSimilarRecipes(id: String) : List<RecipeModel>
}
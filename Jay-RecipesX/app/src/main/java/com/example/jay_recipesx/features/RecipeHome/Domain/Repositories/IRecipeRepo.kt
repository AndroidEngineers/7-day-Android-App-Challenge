package com.example.jay_recipesx.features.RecipeHome.Domain.Repositories

import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel

interface  IRecipeRepo  {
    fun getRandomRecipe(): RecipeModel
    fun getSimilarRecipes(id: String) : List<RecipeModel>
}
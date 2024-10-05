package com.example.jay_recipesx.features.RecipeHome.Domain.Usecases

import com.example.jay_recipesx.features.RecipeHome.Domain.Repositories.IRecipeRepo

class GetSimilarRecipe(private val iRecipeRepo: IRecipeRepo) {
    fun call(id: String) = iRecipeRepo.getSimilarRecipes(id)
}
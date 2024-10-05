package com.example.jay_recipesx.features.RecipeHome.Domain.Usecases

import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel
import com.example.jay_recipesx.features.RecipeHome.Domain.Repositories.IRecipeRepo

class GetRandomRecipe(private val iRecipeRepo: IRecipeRepo) {
    fun call(): RecipeModel = iRecipeRepo.getRandomRecipe()
}
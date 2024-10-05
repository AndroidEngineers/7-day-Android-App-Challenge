package com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel

import com.example.jay_recipesx.features.RecipeHome.Data.Models.RecipeModel


sealed class RecipeHomeState

class InitialRecipeHomeState : RecipeHomeState()

data class RecipeHomeSuccessState(
    val recipes: RecipeModel
) : RecipeHomeState()

data class RecipeHomeErrorState(
    val errorMessage: String
) : RecipeHomeState()

class RecipeHomeLoadingState : RecipeHomeState()

data class RecipeHomeSimilarListSuccessState(
    val recipes: List<RecipeModel>
) : RecipeHomeState()

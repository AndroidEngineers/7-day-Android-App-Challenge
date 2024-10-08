package com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel

sealed class RecipeHomeEvent {

    class GetRandomRecipeEvent() : RecipeHomeEvent()
    class GetSimilarRecipeEvent(id: String) : RecipeHomeEvent()
}
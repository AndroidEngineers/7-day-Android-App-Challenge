package com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jay_recipesx.features.RecipeHome.Data.Repositories.RecipeRepoImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    // State Flow
    private val iRecipeRepo = RecipeRepoImpl()
    private val _recipes = MutableStateFlow<RecipeHomeState>(InitialRecipeHomeState())
    val recipes: StateFlow<RecipeHomeState> = _recipes

    init {
        onEvent(RecipeHomeEvent.GetRandomRecipeEvent())
    }

    fun add(event: RecipeHomeEvent) {
        onEvent(event)
    }

    private fun onEvent(event: RecipeHomeEvent) {
        when (event) {
            is RecipeHomeEvent.GetRandomRecipeEvent -> {
                // Launch coroutine to handle API call in a non-blocking way
                viewModelScope.launch {
                    _recipes.value = RecipeHomeLoadingState()
                    delay(3000)
                    try {
                        val recipe = iRecipeRepo.getRandomRecipe()
                        if (recipe != null) {
                            _recipes.value = RecipeHomeSuccessState(recipes = recipe)
                        } else {
                            _recipes.value = RecipeHomeErrorState(errorMessage = "No recipe found")
                        }
                    } catch (e: Exception) {
                        _recipes.value = RecipeHomeErrorState(errorMessage = "Something went wrong: ${e.message}")
                    }
                }
            }
            is RecipeHomeEvent.GetSimilarRecipeEvent -> {
            }
            else -> {

            }
        }
    }
}

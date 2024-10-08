package com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jay_recipesx.features.RecipeHome.Data.Repositories.RecipeRepoImpl
import com.example.jay_recipesx.features.RecipeHome.Domain.Repositories.IRecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val iRecipeRepo : IRecipeRepo
) : ViewModel() {


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
                    try {
                        // API will return list of recipes
                        // we need to extract the first recipe
                        val recipe = iRecipeRepo.getRandomRecipe().recipes.firstOrNull()

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

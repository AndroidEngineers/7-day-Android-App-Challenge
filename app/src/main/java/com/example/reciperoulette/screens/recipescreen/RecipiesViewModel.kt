package com.example.reciperoulette.screens.recipescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciperoulette.data.local.getAllRecipeList
import com.example.reciperoulette.data.local.getNonVegRecipeList
import com.example.reciperoulette.data.local.getVegRecipeList
import com.example.reciperoulette.data.repositoryimpl.RecipesRepositoryImpl
import com.example.reciperoulette.domain.recipesUseCase.RecipesUseCase
import com.example.reciperoulette.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase) :
    ViewModel() {
    private val _recipeList = MutableStateFlow(emptyList<Recipe>())
    val recipeList: StateFlow<List<Recipe>>
        get() = _recipeList

    init {
//       getData()
    }

    fun updateRecipeList(filterCards: FilterCards) {
        when (filterCards) {
            FilterCards.ALL -> {
                viewModelScope.launch {
                    _recipeList.value = recipesUseCase.getRecipeData()
                }

            }

            FilterCards.VEG -> {
                viewModelScope.launch {
                    _recipeList.value = recipesUseCase.getVegData()
                }
            }

            FilterCards.NON_VEG -> {
                viewModelScope.launch {
                    _recipeList.value = recipesUseCase.getNonVegData()
                }
            }
        }
    }
}
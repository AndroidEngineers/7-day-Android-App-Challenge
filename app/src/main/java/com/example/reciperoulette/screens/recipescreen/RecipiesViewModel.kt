package com.example.reciperoulette.screens.recipescreen

import androidx.lifecycle.ViewModel
import com.example.reciperoulette.data.local.getAllRecipeList
import com.example.reciperoulette.data.local.getNonVegRecipeList
import com.example.reciperoulette.data.local.getVegRecipeList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipesViewModel : ViewModel() {
    private val _recipeList = MutableStateFlow(emptyList<String>())
    val recipeList: StateFlow<List<String>>
        get() = _recipeList

    fun updateRecipeList(filterCards: FilterCards) {
        when (filterCards) {
            FilterCards.ALL -> {
                _recipeList.value = getAllRecipeList()
            }

            FilterCards.VEG -> {
                _recipeList.value = getVegRecipeList()
            }

            FilterCards.NON_VEG -> {
                _recipeList.value = getNonVegRecipeList()
            }
        }
    }
}
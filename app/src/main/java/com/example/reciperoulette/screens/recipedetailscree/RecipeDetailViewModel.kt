package com.example.reciperoulette.screens.recipedetailscree

import androidx.lifecycle.ViewModel
import com.example.reciperoulette.data.local.getGradiantList
import com.example.reciperoulette.data.local.getInstruction
import com.example.reciperoulette.data.local.getSummary
import com.example.reciperoulette.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class RecipeDetailViewModel : ViewModel() {
    private val _recipeData = MutableStateFlow(RecipeDetailState())
    val recipeData: StateFlow<RecipeDetailState>
        get() = _recipeData


//    init {
//        _recipeData.value.summary = getSummary()
//        _recipeData.value.instruction = getInstruction()
//        _recipeData.value.gradiant = getGradiantList()
//    }

    fun getRecipe(recipe: Recipe){
        _recipeData.value.summary = recipe.summary
        _recipeData.value.instruction = recipe.instructions
        _recipeData.value.gradiant = recipe.extendedIngredients
        _recipeData.value.recipeName = recipe.title
    }
}
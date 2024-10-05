package com.example.reciperoulette.screens.recipedetailscree

import androidx.lifecycle.ViewModel
import com.example.reciperoulette.data.local.getGradiantList
import com.example.reciperoulette.data.local.getInstruction
import com.example.reciperoulette.data.local.getSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class RecipeDetailViewModel : ViewModel() {
    private val _recipeData = MutableStateFlow(RecipeDetailState())
    val recipeData: StateFlow<RecipeDetailState>
        get() = _recipeData


    init {
        _recipeData.value.summary = getSummary()
        _recipeData.value.instruction = getInstruction()
        _recipeData.value.gradiant = getGradiantList()
    }
}
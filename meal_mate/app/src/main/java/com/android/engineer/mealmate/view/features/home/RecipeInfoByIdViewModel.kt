package com.android.engineer.mealmate.view.features.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.engineer.mealmate.domain.repository.RecipeSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInfoByIdViewModel @Inject constructor(
    private val repository: RecipeSearchRepository
): ViewModel() {

    val isWebViewLoading = mutableStateOf(true)
    val webViewUrl = mutableStateOf("")
    val recipeTitle = mutableStateOf("")

    fun getInformationById(recipeId: Int) {
        viewModelScope.launch {
            try {
                val visitRecipeInfoById = repository.getRecipeInformationById(recipeId = recipeId)

                visitRecipeInfoById.collectLatest { recipeInfoByIdResponse ->
                    recipeTitle.value = recipeInfoByIdResponse.title
                    webViewUrl.value =
                        recipeInfoByIdResponse.spoonacularSourceUrl.ifEmpty { recipeInfoByIdResponse.sourceUrl }
                    isWebViewLoading.value = false
                }
            } catch (e: Exception) {
                Log.d("onResponse", "There is an error")
                isWebViewLoading.value = false
                e.printStackTrace()
            }
        }
    }
}
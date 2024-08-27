package com.example.reciperoulette.presentation.viewModel.recipelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.domain.usecase.RecipeListFromApiUseCase
import com.example.reciperoulette.presentation.ui.base.UiState
import com.example.reciperoulette.presentation.util.AppConstant
import com.example.reciperoulette.presentation.util.DispatcherProvider
import com.example.reciperoulette.presentation.util.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val getRecipeListFromApiUseCase: RecipeListFromApiUseCase,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider,
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Recipe>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Recipe>>> = _uiState


    init {
        fetchRecipeFromApi()
    }

    private fun fetchRecipeFromApi() {
        viewModelScope.launch(dispatcherProvider.main) {
            if(networkHelper.isNetworkAvailable()){
                getRecipeListFromApiUseCase.invoke(AppConstant.RANDOM_RECIPE_COUNT, AppConstant.API_KEY)
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect{
                        _uiState.value = UiState.Success(it.recipes)
                    }
            }else{
                _uiState.value = UiState.Error(AppConstant.NETWORK_CONNECTION_MESSAGE)
            }
        }
    }
}
package com.example.reciperoulette.presentation.ui.recipelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.make_food.ui.commonui.AppBar
import com.example.make_food.ui.commonui.ShowError
import com.example.make_food.ui.commonui.ShowLoading
import com.example.reciperoulette.R
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.presentation.ui.base.UiState
import com.example.reciperoulette.presentation.viewModel.recipelist.RecipeListViewModel

@Composable
fun RecipeListRoute(
    viewModel: RecipeListViewModel,
    navController: NavHostController,
    onRecipeClick: (recipeId: Int) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    Scaffold(topBar = { AppBar(stringResource(id = R.string.recipe_list)) },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                RecipeListScreen(uiState, navController, onRecipeClick)
            }
        })
}


@Composable
fun RecipeListScreen(
    uiState: UiState<List<Recipe>>,
    navController: NavHostController,
    onRecipeClick: (recipeId: Int) -> Unit,
) {
    when (uiState) {
        is UiState.Success -> {
            RecipeListElement(uiState.data, navController, onRecipeClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }
    }
}


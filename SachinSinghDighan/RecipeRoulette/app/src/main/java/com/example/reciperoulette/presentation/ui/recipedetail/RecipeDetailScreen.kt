package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.make_food.ui.commonui.AppBar
import com.example.make_food.ui.commonui.ShowError
import com.example.make_food.ui.commonui.ShowLoading
import com.example.reciperoulette.R
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.presentation.ui.base.UiState
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme
import com.example.reciperoulette.presentation.viewModel.recipedetail.RecipeDetailViewModel


@Composable
fun RecipeDetailRoute(viewModel: RecipeDetailViewModel, navController: NavHostController) {

    val uiStateRecipe by viewModel.uiState.collectAsStateWithLifecycle()

    RecipeRouletteTheme {
        Scaffold(
            topBar = { AppBar(stringResource(id = R.string.recipe_detail)) },
            content = { innerPadding ->
                RecipeDetailScreen(
                    uiStateRecipe,
                    innerPadding,
                )
            }
        )
    }
}


@Composable
fun RecipeDetailScreen(uiStateRecipe: UiState<Recipe>, innerPadding: PaddingValues){
    when (uiStateRecipe) {
        is UiState.Success -> {
            RecipeDetail(uiStateRecipe.data)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiStateRecipe.message)
        }
    }
}

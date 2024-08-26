package com.example.reciperoulette.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reciperoulette.presentation.ui.recipedetail.RecipeDetailRoute
import com.example.reciperoulette.presentation.ui.recipelist.RecipeListRoute
import com.example.reciperoulette.presentation.viewModel.recipedetail.RecipeDetailViewModel
import com.example.reciperoulette.presentation.viewModel.recipelist.RecipeListViewModel

@Composable
fun Navigation(recipeListViewModel: RecipeListViewModel, recipeDetailViewModel: RecipeDetailViewModel) {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Route.RecipeListScreen.route) {
        composable(route = Route.RecipeListScreen.route) { compose1 ->
            RecipeListRoute(recipeListViewModel, navController, onRecipeClick = {
                recipeDetailViewModel.fetchRecipeById(it)
                //navigateToRecipeDetail(this,viewModel, it, navController)

            })
        }
        composable(route = Route.RecipeDetailScreen.route) {
            RecipeDetailRoute(recipeDetailViewModel, navController)
        }
    }




    /*var shouldShowOnBoardingScreen by rememberSaveable {
        mutableStateOf(true)
    }

    Surface(Modifier.padding(top = innerPadding.calculateTopPadding()), color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnBoardingScreen) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnBoardingScreen = false })
        } else {
            RecipeListContent(List(10) { "$it" })
        }
    }*/
}

/*
fun navigateToRecipeDetail(navigate: Any?,viewModel: RecipeListViewModel, recipeId: Int, navController: NavHostController) {

    (navigate as NavGraphBuilder).composable(route = Route.RecipeDetailScreen.route) {
        RecipeDetailRoute(viewModel, navController)
    }
}*/

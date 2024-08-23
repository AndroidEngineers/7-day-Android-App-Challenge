package com.example.reciperoulette.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reciperoulette.presentation.ui.recipedetail.RecipeDetailScreen
import com.example.reciperoulette.presentation.ui.recipelist.RecipeListScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route){
            RecipeListScreen(navController, List(10) { "$it" })
        }
        composable(route = Screen.RecipeDetail.route){
            RecipeDetailScreen(navController)
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
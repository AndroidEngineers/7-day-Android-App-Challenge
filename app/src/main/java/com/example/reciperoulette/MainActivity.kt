package com.example.reciperoulette

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reciperoulette.data.constants.RECIPE_DETAIL_SCREEN
import com.example.reciperoulette.data.constants.RECIPE_SCREEN
import com.example.reciperoulette.screens.recipedetailscree.RecipeDetailScreen
import com.example.reciperoulette.screens.recipescreen.RecipesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RECIPE_SCREEN) {
        composable(route = RECIPE_SCREEN) {
            RecipesScreen() {
                navController.navigate(RECIPE_DETAIL_SCREEN)
            }
        }
        composable(route = RECIPE_DETAIL_SCREEN) {
            RecipeDetailScreen()
        }
    }
}
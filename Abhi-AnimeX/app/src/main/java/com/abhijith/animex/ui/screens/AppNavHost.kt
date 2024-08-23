package com.abhijith.animex.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhijith.animex.ui.screens.animedetails.AnimeDetails
import com.abhijith.animex.ui.screens.animelist.AnimeList

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.AnimeList.route) {
        composable(Screen.AnimeList.route) {
            AnimeList(navController)
        }
        composable(
            route = Screen.AnimeDetails.route,
            arguments = listOf(navArgument("selectedAnimeItem") { type = NavType.StringType })
        ) {
            AnimeDetails()
        }
    }
}
package com.example.anifetch

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.anifetch.ui.screens.AnimeDetailScreen
import com.example.anifetch.ui.screens.MainScreen
import com.example.anifetch.ui.screens.SplashScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            SplashScreen(navController) }
        composable("home") { MainScreen(navController)}
        composable("details/{animeId}"){backstackEntry ->
            val animeId = backstackEntry.arguments?.getString("animeId")?.toIntOrNull()?:0
            AnimeDetailScreen(navController , animeId  )
        }
    }
}
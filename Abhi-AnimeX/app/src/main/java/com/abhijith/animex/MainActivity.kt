package com.abhijith.animex

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhijith.animex.ui.screens.Screen
import com.abhijith.animex.ui.screens.animedetails.AnimeDetails
import com.abhijith.animex.ui.screens.animelist.AnimeList
import com.abhijith.animex.ui.theme.AnimeXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.WHITE),
            navigationBarStyle = SystemBarStyle.dark(Color.WHITE)
        )
        setContent {
            AnimeXTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .background(color = androidx.compose.ui.graphics.Color.White)
                    ) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}


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
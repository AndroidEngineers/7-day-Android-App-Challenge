package com.example.cinescope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinescope.ui.theme.CineScopeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineScopeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    CineScopeApp()
                }
            }
        }
    }
}

@Composable
fun CineScopeApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {

        composable(route = "home_screen") {
            HomeScreen(
                navigateToDetailsScreen = { navController.navigate("details_screen") }
            )
        }

        composable(route = "details_screen") {
            DetailsScreen(
                navigateToHomeScreen = { navController.navigate("home_screen") }
            )
        }
    }
}

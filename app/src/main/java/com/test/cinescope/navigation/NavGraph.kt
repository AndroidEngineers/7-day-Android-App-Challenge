package com.test.cinescope.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.cinescope.screens.BottomNavigationScreen
import com.test.cinescope.screens.SplashScreen

@Composable

fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {
        composable(route = Routes.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Routes.BottomNavBar.route)
            }
        }
        composable(route = Routes.BottomNavBar.route) {
          BottomNavigationScreen()
        }
    }
}


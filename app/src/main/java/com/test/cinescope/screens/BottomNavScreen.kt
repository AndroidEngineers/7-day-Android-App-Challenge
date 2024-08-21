package com.test.cinescope.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.test.cinescope.data.BottomNavigationItem
import com.test.cinescope.navigation.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen() {
    var bottomNavController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(navController = bottomNavController)
    }) {
        NavHost(navController = bottomNavController, startDestination = Routes.HomeScreen.route) {
            composable(Routes.HomeScreen.route) {
                HomeScreen()
            }
            composable(Routes.NotificationScreen.route) {
                NotificationScreen()
            }
            composable(Routes.FavouriteScreen.route) {
                FavouriteScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var backStackEntry = navController.currentBackStackEntryAsState()

    var navItem = listOf(
        BottomNavigationItem("Home", Icons.Rounded.Home, Routes.HomeScreen.route),
        BottomNavigationItem(
            "Notification",
            Icons.Rounded.Notifications,
            Routes.NotificationScreen.route
        ),
        BottomNavigationItem("Favourite", Icons.Rounded.Favorite, Routes.FavouriteScreen.route)
    )

    BottomAppBar {
        navItem.forEach {
            var selected = it.route == backStackEntry.value?.destination?.route
            NavigationBarItem(selected = selected, onClick = {
                navController.navigate(it.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            }, icon = { Icon(imageVector = it.icon, contentDescription = null) })
        }
    }
}

package com.mani.quotify007.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import com.mani.quotify007.ui.screens.bottomappbar.BottomNavItem
import com.mani.quotify007.ui.screens.favorites.FavoritesScreen
import com.mani.quotify007.ui.screens.home.HomeScreen
import com.mani.quotify007.ui.screens.search.SearchScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    NavHost(navController, startDestination = BottomNavItem.HOME.route, modifier = modifier) {
        composable(BottomNavItem.HOME.route) {
            HomeScreen(
                state,
                onEvent
            )
        }
        composable(BottomNavItem.SEARCH.route) {
            SearchScreen(
                state,
                onEvent
            )
        }
        composable(BottomNavItem.FAVORITES.route) {
            FavoritesScreen(
                state,
                onEvent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationGraphPreview() {
    val navController = rememberNavController()
    NavigationGraph(
        navController = navController,
        state = MainState(),
        onEvent = {}
    )
}
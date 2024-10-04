package com.mani.quotify007.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import com.mani.quotify007.ui.screens.favorites.FavoritesScreen
import com.mani.quotify007.ui.screens.home.HomeScreen
import com.mani.quotify007.ui.screens.search.SearchScreen
import com.mani.quotify007.ui.screens.bottomappbar.BottomNavItem

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    state: MainState,
    quotes: List<Quote>,
    favoriteQuotes: List<Quote>,
    onEvent: (MainEvent) -> Unit
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        composable(BottomNavItem.Home.route) { HomeScreen(state, onEvent) }
        composable(BottomNavItem.Search.route) { SearchScreen(quotes, onEvent) }
        composable(BottomNavItem.Favorites.route) { FavoritesScreen(favoriteQuotes, onEvent) }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationGraphPreview() {
    val navController = rememberNavController()
    NavigationGraph(
        navController = navController,
        state = MainState(),
        quotes = listOf(Quote("Sample quote", "Sample Author")),
        favoriteQuotes = listOf(Quote("Sample favorite quote", "Sample Author")),
        onEvent = {}
    )
}
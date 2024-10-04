package com.mani.quotify007.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.view.utils.BottomNavItem

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    quotes: List<String>,
    favoriteQuotes: List<String>,
    addFavorite: (String) -> Unit,
    removeFavorite: (String) -> Unit
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        composable(BottomNavItem.Home.route) { HomeScreen(quotes, addFavorite) }
        composable(BottomNavItem.Search.route) { SearchScreen(quotes, addFavorite) }
        composable(BottomNavItem.Favorites.route) { FavoritesScreen(favoriteQuotes, removeFavorite) }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationGraphPreview() {
    val navController = rememberNavController()
    NavigationGraph(
        navController = navController,
        quotes = listOf("Sample quote"),
        favoriteQuotes = listOf("Sample favorite quote"),
        addFavorite = {},
        removeFavorite = {}
    )
}
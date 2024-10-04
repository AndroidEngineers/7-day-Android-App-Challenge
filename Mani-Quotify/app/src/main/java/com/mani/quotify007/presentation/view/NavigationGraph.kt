package com.mani.quotify007.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.presentation.view.utils.BottomNavItem

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    favoriteQuotes: List<Quote>,
    addFavorite: (Quote) -> Unit,
    removeFavorite: (Quote) -> Unit
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
        quotes = listOf(Quote("Sample quote")),
        favoriteQuotes = listOf(Quote("Sample favorite quote")),
        addFavorite = {},
        removeFavorite = {}
    )
}
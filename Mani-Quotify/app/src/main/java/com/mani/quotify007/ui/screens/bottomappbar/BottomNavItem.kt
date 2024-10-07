package com.mani.quotify007.ui.screens.bottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home, "home"),
    SEARCH("Search", Icons.Filled.Search, Icons.Filled.Search, "search"),
    FAVORITES("Favorites", Icons.Filled.Favorite, Icons.Filled.FavoriteBorder, "favorites")
}
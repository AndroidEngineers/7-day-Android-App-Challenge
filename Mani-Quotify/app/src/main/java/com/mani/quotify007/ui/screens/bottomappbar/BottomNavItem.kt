package com.mani.quotify007.ui.screens.bottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {
    data object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    data object Search : BottomNavItem("Search", Icons.Default.Search, "search")
    data object Favorites : BottomNavItem("Favorites", Icons.Default.Favorite, "favorites")
}
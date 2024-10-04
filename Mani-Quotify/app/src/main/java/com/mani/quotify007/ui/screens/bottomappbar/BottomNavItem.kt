package com.mani.quotify007.ui.screens.bottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector, val route: String) {
    data object Home : BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home, "home")
    data object Search : BottomNavItem("Search", Icons.Filled.Search, Icons.Outlined.Search, "search")
    data object Favorites : BottomNavItem("Favorites", Icons.Filled.Favorite, Icons.Outlined.Favorite, "favorites")
}
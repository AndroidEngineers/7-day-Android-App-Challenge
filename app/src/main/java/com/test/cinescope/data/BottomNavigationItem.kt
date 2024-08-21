package com.test.cinescope.data

import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
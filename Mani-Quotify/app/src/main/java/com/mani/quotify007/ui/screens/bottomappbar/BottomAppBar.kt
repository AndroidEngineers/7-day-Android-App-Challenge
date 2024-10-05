package com.mani.quotify007.ui.screens.bottomappbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun BottomAppBar(navController: NavHostController?) {
    val items = listOf(
        BottomNavItem.HOME,
        BottomNavItem.SEARCH,
        BottomNavItem.FAVORITES
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                label = { Text(item.title) },
                onClick = {
                    selectedItemIndex = index
                    navController?.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(if(index == selectedItemIndex) item.selectedIcon else item.unselectedIcon, contentDescription = item.title) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomAppBarPreview() {
    BottomAppBar(navController = null)
}
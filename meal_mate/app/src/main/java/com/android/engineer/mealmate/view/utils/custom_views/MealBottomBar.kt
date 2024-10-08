package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.utils.constants.nav.BottomBarScreen

@Composable
fun MealBottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.MealPlan,
        /*BottomBarScreen.ShopList, // These items will be available in Phase 2.
        BottomBarScreen.Report,*/
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    // State of bottomBar, set state to false, if current page route is not contains in the screens list.
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val bottomBarDestination = screens.any {
        bottomBarState.value = it.route == currentDestination?.route
        it.route == currentDestination?.route
    }
    if(bottomBarDestination) {
        AnimatedVisibility(
            visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {
                NavigationBar(
                    containerColor = OrangePrimary
                ) {
                    screens.forEach { tabItem ->
                        AddItem(
                            screen = tabItem,
                            currentDestination = currentDestination,
                            navHostController = navHostController
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    NavigationBarItem(
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = stringResource(id = R.string.app_name)
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = Color.White.copy(alpha = 0.5f),
            indicatorColor = OrangePrimary,
            selectedTextColor = Color.White,
            unselectedTextColor =  Color.White.copy(alpha = 0.5f)
        ),
        label = {
            Text(text = screen.title)
        },
        alwaysShowLabel = true,
        onClick = {
            navHostController.navigate(screen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re-selecting the same item
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                restoreState = true
            }
        }
    )
}
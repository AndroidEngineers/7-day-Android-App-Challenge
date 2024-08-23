package com.android.engineer.mealmate.view.utils.custom_views

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.BottomBarScreen

@Composable
fun MealBottomBar(navHostController: NavHostController, bottomBarState: MutableState<Boolean>) {
    val screens = mutableListOf(
        BottomBarScreen.Home,
        BottomBarScreen.MealPlan,
        BottomBarScreen.ShopList,
        BottomBarScreen.Report,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

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
                BottomAppBar(
                    modifier =
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Modifier
                            .padding(28.dp)
                            .clip(shape = RoundedCornerShape(corner = CornerSize(24.dp)))
                            .height(64.dp)

                    } else {
                        Modifier
                            .padding(bottom = 58.dp, start = 28.dp, end = 28.dp)
                            .clip(shape = RoundedCornerShape(corner = CornerSize(24.dp)))
                            .height(75.dp)
                    },
                    containerColor = OrangePrimary,
                ) {
                    screens.forEach { screen ->
                        AddItem(
                            screen = screen,
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
            indicatorColor = OrangePrimary
        ),
        alwaysShowLabel = false,
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
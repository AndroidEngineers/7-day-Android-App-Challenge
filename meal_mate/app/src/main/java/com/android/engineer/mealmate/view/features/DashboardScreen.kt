package com.android.engineer.mealmate.view.features

import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.utils.constants.nav.graph.DashboardNavGraph
import com.android.engineer.mealmate.view.utils.custom_views.MealBottomBar

@Composable
fun DashboardScreen(
    navHostController: NavHostController = rememberNavController(),
    logout: () -> Unit
) {
    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = Modifier
            .background(OrangeOnPrimary),
        bottomBar = {
            MealBottomBar(navHostController = navHostController, bottomBarState = bottomBarState)
        }
    ) { paddingValues ->
        DashboardNavGraph(
            paddingValues = paddingValues,
            navHostController = navHostController,
            logout = logout
        )
    }
}

@Composable
@Preview()
fun DashboardPreview() {
    DashboardScreen(navHostController = rememberNavController(), logout = {})
}
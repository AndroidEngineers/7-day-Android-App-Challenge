package com.android.engineer.mealmate.view.utils.constants.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.engineer.mealmate.view.features.home.HomeScreen
import com.android.engineer.mealmate.view.features.meal_plan.MealPlanScreen
import com.android.engineer.mealmate.view.features.profile.ProfileScreen
import com.android.engineer.mealmate.view.features.report.ReportScreen
import com.android.engineer.mealmate.view.features.shop_list.ShopListScreen
import com.android.engineer.mealmate.view.utils.constants.Graph
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.BottomBarScreen

@Composable
fun DashboardNavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    logout: () -> Unit
) {
    NavHost(
        navController = navHostController,
        route = Graph.DASHBOARD,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                title = BottomBarScreen.Home.title,
                navHostController = navHostController
            )
        }
        composable(route = BottomBarScreen.MealPlan.route) {
            MealPlanScreen(
                title = BottomBarScreen.MealPlan.title,
                navHostController = navHostController
            )
        }
        composable(route = BottomBarScreen.ShopList.route) {
            ShopListScreen(
                title = BottomBarScreen.ShopList.title,
                navHostController = navHostController
            )
        }
        composable(route = BottomBarScreen.Report.route) {
            ReportScreen(
                title = BottomBarScreen.Report.title,
                navHostController = navHostController
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                title = BottomBarScreen.Profile.title,
                navHostController = navHostController
            )
        }
    }
}
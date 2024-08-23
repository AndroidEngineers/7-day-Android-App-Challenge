package com.android.engineer.mealmate.view.utils.constants.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.engineer.mealmate.view.features.home.HomeScreen
import com.android.engineer.mealmate.view.features.home.RecipeDetailsScreen
import com.android.engineer.mealmate.view.features.meal_plan.MealPlanScreen
import com.android.engineer.mealmate.view.features.profile.ProfileScreen
import com.android.engineer.mealmate.view.features.report.ReportScreen
import com.android.engineer.mealmate.view.features.shop_list.ShopListScreen
import com.android.engineer.mealmate.view.utils.constants.DASHBOARD
import com.android.engineer.mealmate.view.utils.constants.RECIPE_DETAILS
import com.android.engineer.mealmate.view.utils.constants.STATIC_URL1
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.BottomBarScreen

@Composable
fun DashboardNavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    logout: () -> Unit
) {
    NavHost(
        navController = navHostController,
        route = DASHBOARD,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                navHostController = navHostController,
                userName = "",
                paddingValues = paddingValues
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
        composable(route = RECIPE_DETAILS) {
            RecipeDetailsScreen(
                title = "Web View",
                webUrl = STATIC_URL1,
                navHostController = navHostController,

            )
        }
    }
}
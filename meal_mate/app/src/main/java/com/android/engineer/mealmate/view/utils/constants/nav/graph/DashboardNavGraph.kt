package com.android.engineer.mealmate.view.utils.constants.nav.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.engineer.mealmate.view.features.home.HomeScreen
import com.android.engineer.mealmate.view.features.home.RECIPE_ID
import com.android.engineer.mealmate.view.features.home.RECIPE_ID_START_END
import com.android.engineer.mealmate.view.features.home.RecipeDetailsScreen
import com.android.engineer.mealmate.view.features.meal_plan.MealPlanScreen
import com.android.engineer.mealmate.view.features.profile.ProfileScreen
import com.android.engineer.mealmate.view.utils.constants.nav.BottomBarScreen

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
                paddingValues = paddingValues
            )
        }
        composable(route = BottomBarScreen.MealPlan.route) {
            MealPlanScreen(
                navHostController = navHostController
            )
        }
        /*composable(route = BottomBarScreen.ShopList.route) {
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
        }*/
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                navHostController = navHostController,
                logout = logout
            )
        }
        composable(
            route = RECIPE_DETAILS.plus(RECIPE_ID_START_END),
            arguments = listOf(
                navArgument(RECIPE_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt(RECIPE_ID) ?: 0

            RecipeDetailsScreen(
                recipeId = recipeId,
                navHostController = navHostController
            )
        }
    }
}
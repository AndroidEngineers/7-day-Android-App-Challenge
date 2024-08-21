package com.android.engineer.mealmate.view.utils.constants.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.engineer.mealmate.view.features.DashboardScreen
import com.android.engineer.mealmate.view.utils.constants.Graph
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.AuthScreen

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {

        authNavGraph(navHostController = navHostController)
        composable(route = Graph.DASHBOARD) {
            DashboardScreen(
                logout = {
                    navHostController.navigate(AuthScreen.Login.route) {
                        popUpTo(0){}
                    }
                }
            )
        }
    }
}

// For the single viewModel, use this function to get the back stack entry
@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
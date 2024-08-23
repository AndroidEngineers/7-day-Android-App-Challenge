package com.android.engineer.mealmate.view.utils.constants.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.android.engineer.mealmate.view.features.auth.LoginScreen
import com.android.engineer.mealmate.view.utils.constants.AUTHENTICATION
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.AuthScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        route = AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(title = AuthScreen.Login.title, navHostController = navHostController)
        }
    }
}
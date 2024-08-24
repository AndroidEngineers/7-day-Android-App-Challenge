package com.android.engineer.mealmate.view.utils.constants.nav

sealed class AuthScreen(val route: String, val title: String) {
    data object Login: AuthScreen(route = "LOGIN", title = "LOGIN")
}
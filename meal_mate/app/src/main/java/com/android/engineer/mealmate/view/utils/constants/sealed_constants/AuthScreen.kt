package com.android.engineer.mealmate.view.utils.constants.sealed_constants

sealed class AuthScreen(val route: String, val title: String) {
    data object Login: AuthScreen(route = "LOGIN", title = "LOGIN")
    data object Signup: AuthScreen(route = "SIGNUP", title = "SIGNUP")
    data object Start: AuthScreen(route = "START", title = "START")
}
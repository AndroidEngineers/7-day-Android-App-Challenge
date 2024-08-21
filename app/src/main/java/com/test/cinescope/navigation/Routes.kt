package com.test.cinescope.navigation

sealed class Routes(var route : String) {
    object SplashScreen : Routes("splash_screen")
    object BottomNavBar : Routes("bottom_nav_bar")
    object HomeScreen : Routes("home_screen")
    object NotificationScreen : Routes("notification_screen")
    object FavouriteScreen : Routes("favourite_screen")
}
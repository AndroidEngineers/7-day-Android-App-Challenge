package com.android.engineer.mealmate.view.utils.constants.nav

import com.android.engineer.mealmate.R

/*
 * Created a navigation bar items - route name, title and icons
 */
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home: BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = R.drawable.ic_home
    )
    data object MealPlan: BottomBarScreen(
        route = "MEAL",
        title = "MEAL PLAN",
        icon = R.drawable.ic_meal_plan
    )
    data object ShopList: BottomBarScreen(
        route = "SHOP",
        title = "SHOP LIST",
        icon = R.drawable.ic_shopping_list
    )
    data object Report: BottomBarScreen(
        route = "REPORT",
        title = "REPORT",
        icon = R.drawable.ic_report
    )
    data object Profile: BottomBarScreen(
        route = "PROFILE",
        title = "PROFILE",
        icon = R.drawable.ic_profile
    )
}
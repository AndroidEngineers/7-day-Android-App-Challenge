package com.android.engineer.mealmate.view.features.meal_plan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.utils.constants.nav.BottomBarScreen
import com.android.engineer.mealmate.view.utils.custom_views.MealCalender

@Composable
fun MealPlanScreen(title: String, navHostController: NavHostController) {
   Column(
       modifier = Modifier
           .fillMaxSize()
           .background(OrangeOnPrimary),
       horizontalAlignment = Alignment.CenterHorizontally,
   ) {
	   MealCalender(
		   modifier = Modifier.padding(top = 30.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
	   )
   }
}


@Composable
@Preview()
fun MealPlanScreenPreview() {
    MealPlanScreen(title = BottomBarScreen.MealPlan.title, navHostController = rememberNavController())
}
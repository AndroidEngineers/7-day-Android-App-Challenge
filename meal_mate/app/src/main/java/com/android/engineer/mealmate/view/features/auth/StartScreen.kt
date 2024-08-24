package com.android.engineer.mealmate.view.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.AuthScreen
import com.android.engineer.mealmate.view.utils.custom_views.MealFilledButton
import com.android.engineer.mealmate.view.utils.custom_views.MealText
import com.android.engineer.mealmate.view.utils.custom_views.MealTextButton

@Composable
fun StartScreen(navHostController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeOnPrimary)
            .padding(top = 64.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MealText(
            text = stringResource(id = R.string.welcome_to_meal_mate),
            fontSize = 42.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            MealText(
                text = stringResource(id = R.string.organize_your_day),
                fontSize = 32.sp,
                textAlign = TextAlign.Start,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly

        ) {
            MealFilledButton(
                onClick = { navHostController.navigate(AuthScreen.Login.route) },
                text = stringResource(id = R.string.login),
                modifier = Modifier.fillMaxWidth()
            )
            MealTextButton(
                onClick = { navHostController.navigate(AuthScreen.Signup.route)},
                text = stringResource(id = R.string.sign_up),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    StartScreen(navHostController = rememberNavController())
}
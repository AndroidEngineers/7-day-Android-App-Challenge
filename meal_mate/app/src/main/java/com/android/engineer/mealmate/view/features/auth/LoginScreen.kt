package com.android.engineer.mealmate.view.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.view.utils.constants.nav.AuthScreen
import com.android.engineer.mealmate.view.utils.constants.nav.DASHBOARD

@Composable
fun LoginScreen(title: String, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navHostController.navigate(route = DASHBOARD)
            }
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title
        )
    }
}


@Composable
@Preview()
fun LoginScreenPreview() {
    LoginScreen(title = AuthScreen.Login.title, navHostController = rememberNavController())
}
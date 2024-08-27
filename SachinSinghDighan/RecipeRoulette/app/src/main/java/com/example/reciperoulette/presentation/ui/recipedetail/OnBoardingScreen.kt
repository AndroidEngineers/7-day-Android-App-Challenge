package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.presentation.ui.commonui.ScreenContentText
import com.example.reciperoulette.R

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScreenContentText(text = stringResource(id = R.string.app_name))
        Button(
            modifier = modifier.padding(24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }

}
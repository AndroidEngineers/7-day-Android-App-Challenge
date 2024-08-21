package com.android.engineer.mealmate.view.features.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.BottomBarScreen

@Composable
fun ReportScreen(title: String, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeOnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(title)
    }
}


@Composable
@Preview()
fun ReportScreenPreview() {
    ReportScreen(title = BottomBarScreen.Report.title, navHostController = rememberNavController())
}
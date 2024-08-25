package com.example.taskninja20.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskninja20.R
import com.example.taskninja20.reusable.CustomTextBold
import com.example.taskninja20.reusable.CategoryGridScreen

@Composable
fun DashboardScreen(modifier: Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column() {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            CustomTextBold(
                text = stringResource(id = R.string.dashboard),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )
            CategoryGridScreen()
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
            AddTaskButton(modifier= modifier.height(50.dp).fillMaxWidth()
                .padding(start = 16.dp,end = 16.dp))

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun preview() {
    DashboardScreen(modifier = Modifier)
}
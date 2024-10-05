package com.example.studybuddy.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Circle(
)
{
    Card(
        modifier = Modifier
            .size(40.dp)
            .background(color = Color.Gray, shape = CircleShape)
    ) {

    }
}

@Composable
fun ShowProgress()
{
    Row(modifier = Modifier.fillMaxSize()) {
Circle()
    }
}


@Preview
@Composable
fun ShowProgressPreview()
{
    ShowProgress()

}
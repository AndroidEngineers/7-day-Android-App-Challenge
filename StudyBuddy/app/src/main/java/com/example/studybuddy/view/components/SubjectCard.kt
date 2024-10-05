package com.example.studybuddy.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.studybuddy.R

@Composable
fun SubjectCard(
    subjectName: String,
    gradientColors:List<Color>,
    onClick: () -> Unit
){
    Box(modifier = Modifier
        .size(150.dp).clickable { onClick() }
        .background(brush = Brush.verticalGradient(gradientColors),
            shape = MaterialTheme.shapes.medium
        )
    )
    {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.books),
                contentDescription ="",
                modifier = Modifier.size(80.dp))

            Text(text = subjectName,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                maxLines = 1)
        }
    }
}
package com.abhijith.animex.ui.screens.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abhijith.animex.R

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://i.pinimg.com/564x/48/e3/7a/48e37a099cf6ea3828ef8a9df963daa6.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = "Error Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(R.font.montserrat_regular)
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen("Something went wrong!!! \n Please try again")
}
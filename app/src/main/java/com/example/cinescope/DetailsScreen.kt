package com.example.cinescope

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinescope.ui.theme.CineScopeTheme

@Composable
fun DetailsScreen(navigateToHomeScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            IconButton(
                onClick = {
                    navigateToHomeScreen()
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }

            Text(
                text = "URI: the surgical strike",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )

            Image(
                painter = painterResource(R.drawable.uri_movie_banner),
                contentDescription = "uri_movie_banner",
                modifier = Modifier
                    .size(height = 200.dp, width = 320.dp)
            )

            Text(
                text = "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here" +
                        "Short Description should be here Short Description should be here",
                modifier = Modifier
                    .padding(8.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BottomBar()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview2() {
    CineScopeTheme {
        DetailsScreen {  }
    }
}
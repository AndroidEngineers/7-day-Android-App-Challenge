package com.example.cinescope

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinescope.ui.theme.CineScopeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetailsScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Search bar
            var searchText by remember { mutableStateOf("") }
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search movie") },
                modifier = modifier
                    .padding(24.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(height = 50.dp, width = 310.dp)
            )

            // Movie list
            MovieList(navigateToDetailsScreen)
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

@Composable
fun MovieList(
    navigateToDetailsScreen: () -> Unit
) {
    val movieList = mutableListOf("Uri", "Bang Bang", "Hathe mara sathe", "Republic")
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(movieList) {
            item ->
            MovieItem(item, navigateToDetailsScreen)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MovieItem(
    title: String,
    navigateToDetailsScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { navigateToDetailsScreen() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.Start)
        )

        Image(
            painter = painterResource(R.drawable.uri_movie_banner),
            contentDescription = "uri_movie_banner",
            modifier = Modifier
                .size(height = 200.dp, width = 320.dp)
        )

        Text(
            text = "Short Description should be here",
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.Start)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    CineScopeTheme {
        HomeScreen(navigateToDetailsScreen = {} )
    }
}
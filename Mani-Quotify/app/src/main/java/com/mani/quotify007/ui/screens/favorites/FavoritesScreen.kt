package com.mani.quotify007.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import com.mani.quotify007.ui.screens.quote.QuotesScreen

@Composable
fun FavoritesScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    if (state.favoriteQuotes.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No favorite quotes available.")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(state.favoriteQuotes) { quote ->
                    QuotesScreen(
                        quote,
                        onEvent = onEvent,
                        false
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(
        MainState(),
        onEvent = {}
    )
}
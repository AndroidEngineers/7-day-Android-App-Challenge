package com.mani.quotify007.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import com.mani.quotify007.ui.screens.bottomappbar.BottomAppBar
import com.mani.quotify007.ui.theme.QuotifyAppTheme

@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit,
    onCopyText: (Quote) -> Unit,
    onShareClick: (Quote) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomAppBar(navController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            state = state,
            quotes = state.quotes,
            favoriteQuotes = state.favoriteQuotes,
            onEvent = onEvent,
            onCopyText = onCopyText,
            onShareClick = onShareClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    QuotifyAppTheme {
        MainScreen(
            state = MainState(),
            onEvent = {},
            onCopyText = {},
            onShareClick = {}
        )
    }
}
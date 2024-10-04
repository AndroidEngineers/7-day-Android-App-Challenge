package com.mani.quotify007.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.presentation.ui.theme.QuotifyAppTheme
import com.mani.quotify007.presentation.viewmodel.MainEvent
import com.mani.quotify007.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    Scaffold(
        bottomBar = { BottomAppBar(navController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            quotes = state.quotes,
            favoriteQuotes = state.favoriteQuotes,
            addFavorite = { quote -> viewModel.onEvent(MainEvent.AddFavorite(quote)) },
            removeFavorite = { quote -> viewModel.onEvent(MainEvent.RemoveFavorite(quote)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    QuotifyAppTheme {
        MainScreen()
    }
}
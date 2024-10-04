package com.mani.quotify007.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.ui.navigation.viewmodel.MainViewModel
import com.mani.quotify007.ui.screens.bottomappbar.BottomAppBar
import com.mani.quotify007.ui.theme.QuotifyAppTheme

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    /* Collect the current state from the ViewModel as a State object.
    * the state is preserved across configuration changes and the UI is updated reactively */
    val state = viewModel.state.collectAsState().value
    Scaffold(
        bottomBar = { BottomAppBar(navController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            state = state,
            quotes = state.quotes,
            favoriteQuotes = state.favoriteQuotes,
            onEvent = { event -> viewModel.onEvent(event) }
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
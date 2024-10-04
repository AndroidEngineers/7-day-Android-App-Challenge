package com.mani.quotify007.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.data.repository.QuoteRepositoryImpl
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.presentation.ui.theme.QuotifyAppTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val repository = QuoteRepositoryImpl()
    val getQuoteUseCase = GetQuoteUseCase(repository = repository)
    val quotes = remember {
        mutableStateListOf(*getQuoteUseCase.execute().toTypedArray())
    }
    val favoriteQuotes = remember { mutableStateListOf<Quote>() }
    Scaffold(
        bottomBar = { BottomAppBar(navController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            quotes = quotes,
            favoriteQuotes = favoriteQuotes,
            addFavorite = { quote -> favoriteQuotes.add(quote) },
            removeFavorite = { quote -> favoriteQuotes.remove(quote) }
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
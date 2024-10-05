package com.mani.quotify007.ui.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val getQuoteUseCase = (application as QuotifyApp).getQuoteUseCase

    init {
        _state.value = MainState(
            quotes = getQuoteUseCase.execute(),
            randomQuote = getQuoteUseCase.execute().randomOrNull()
        )
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.AddFavorite -> {
                _state.value = _state.value.copy(
                    favoriteQuotes = _state.value.favoriteQuotes + event.quote
                )
            }
            is MainEvent.RemoveFavorite -> {
                _state.value = _state.value.copy(
                    favoriteQuotes = _state.value.favoriteQuotes - event.quote
                )
            }
            is MainEvent.GetRandomQuote -> {
                val randomQuote = _state.value.quotes.randomOrNull()
                _state.value = _state.value.copy(randomQuote = randomQuote)
            }
        }
    }
}
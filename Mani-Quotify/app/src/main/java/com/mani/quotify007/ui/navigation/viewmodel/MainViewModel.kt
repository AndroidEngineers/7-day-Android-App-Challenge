package com.mani.quotify007.ui.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.data.local.FavoriteQuoteEntity
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val getQuoteUseCase = (application as QuotifyApp).getQuoteUseCase

    init {
        viewModelScope.launch {
            (application as QuotifyApp).quoteDb.favoriteQuoteDao().getAllFavoriteQuotes()
                .collect { quotes ->
                    _state.value =
                        _state.value.copy(favoriteQuotes = quotes.map { it.toDomainModel() })
                    _state.value = _state.value.copy(
                        quotes = getQuoteUseCase.execute().map { quote ->
                            if (state.value.favoriteQuotes.any { it.id == quote.id }) {
                                quote.isFavorite = true
                            } else {
                                quote.isFavorite = false
                            }
                            quote
                        },
                        randomQuote = getQuoteUseCase.execute().randomOrNull()
                    )
                }
        }
    }

    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.AddFavorite -> {
                    (application as QuotifyApp).quoteDb.favoriteQuoteDao()
                        .insertFavoriteQuote(event.quote.toEntity())
                    _state.value = _state.value.copy(
                        favoriteQuotes = _state.value.favoriteQuotes + event.quote
                    )
                }
                is MainEvent.RemoveFavorite -> {
                    (application as QuotifyApp).quoteDb.favoriteQuoteDao()
                        .deleteFavoriteQuote(event.quote.toEntity())
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

    private fun FavoriteQuoteEntity.toDomainModel() = Quote(id, text, author, true)

    private fun Quote.toEntity() = FavoriteQuoteEntity(id = id, text = text, author = author ?: "")
}
package com.mani.quotify007.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mani.quotify007.data.repository.QuoteRepositoryImpl
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class MainState(
    val quotes: List<Quote> = emptyList(),
    val favoriteQuotes: List<Quote> = emptyList()
)

sealed class MainEvent {
    data class AddFavorite(val quote: Quote) : MainEvent()
    data class RemoveFavorite(val quote: Quote): MainEvent()
}

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val repository = QuoteRepositoryImpl()
    private val getQuoteUseCase = GetQuoteUseCase(repository = repository)

    init {
        _state.value = MainState(
            quotes = getQuoteUseCase.execute()
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
        }
    }
}
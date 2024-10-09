package com.mani.quotify007.ui.navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetQuoteUseCase) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val _copyTextEvent = MutableLiveData<Quote>()
    val copyTextEvent: LiveData<Quote> = _copyTextEvent

    private val _shareClickEvent = MutableLiveData<Quote>()
    val shareClickEvent: LiveData<Quote> = _shareClickEvent

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> = _showToast

    init {
        loadQuotesData()
    }

    private fun loadQuotesData() {
        viewModelScope.launch {
            _state.value.isLoading = true
            try {
                useCase.dbQuotes()
                    .catch { e -> e.printStackTrace() }
                    .collect { quotes ->
                        _state.value =
                            _state.value.copy(favoriteQuotes = quotes)
                        _state.value = _state.value.copy(
                            quotes = useCase.result().results.map { quote ->
                                if (state.value.favoriteQuotes.any { it.id == quote.id }) {
                                    quote.isFavorite = true
                                } else {
                                    quote.isFavorite = false
                                }
                                quote
                            }
                        )
                        _state.value.isLoading = false
                    }
            } catch (e: Exception) {
                _showToast.postValue(e.message)
            } finally {
                _state.value.isLoading = false
            }
        }
    }

    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.AddFavorite -> {
                    useCase.addFavoriteQuote(event.quote)
                    _state.value = _state.value.copy(
                        favoriteQuotes = _state.value.favoriteQuotes + event.quote
                    )
                }
                is MainEvent.RemoveFavorite -> {
                    useCase.removeFavoriteQuote(event.quote)
                    _state.value = _state.value.copy(
                        favoriteQuotes = _state.value.favoriteQuotes - event.quote
                    )
                }
                is MainEvent.GetRandomQuote -> {
                    val randomQuote = _state.value.quotes.randomOrNull()
                    _state.value = _state.value.copy(randomQuote = randomQuote)
                }

                is MainEvent.CopyText -> _copyTextEvent.postValue(event.quote)
                is MainEvent.ShareClick -> _shareClickEvent.postValue(event.quote)
                is MainEvent.ShowToast -> _showToast.postValue(event.message)
            }
        }
    }
}
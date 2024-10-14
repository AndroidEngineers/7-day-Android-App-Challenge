package com.mani.quotify007.ui.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import com.mani.quotify007.ui.navigation.model.ResponseResult
import com.mani.quotify007.ui.navigation.model.UiActionEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetQuoteUseCase) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val _uiActionEvent = MutableSharedFlow<UiActionEvent>()
    val uiActionEvent: SharedFlow<UiActionEvent?> = _uiActionEvent

    init {
        loadQuotesData()
    }

    fun loadQuotesData() {
        viewModelScope.launch {
            _state.value.isLoading = true
            useCase.dbQuotes()
                .catch { e -> e.printStackTrace() }
                .collect { quotes ->
                    when(val result = useCase.result()) {
                        is ResponseResult.Success -> {
                            _state.value = _state.value.copy(
                                favoriteQuotes = quotes,
                                quotes = result.data.results.map { quote ->
                                    quote.isFavorite = quotes.any { it.id == quote.id }
                                    quote
                                },
                                isLoading = false
                            )
                        }
                        is ResponseResult.Error -> {
                            _state.value = _state.value.copy(
                                favoriteQuotes = quotes,
                                isLoading = false
                            )
                            _uiActionEvent.emit(
                                UiActionEvent.ShowToast(result.exception.message.toString())
                            )
                        }
                    }
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
                is MainEvent.CopyText -> _uiActionEvent.emit(UiActionEvent.CopyText(event.quote))

                is MainEvent.ShareClick -> _uiActionEvent.emit(UiActionEvent.ShareClick(event.quote))

                is MainEvent.ShowToast -> _uiActionEvent.emit(UiActionEvent.ShowToast(event.message))
            }
        }
    }
}
package com.mani.quotify007.ui.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.data.local.FavoriteQuoteEntity
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.ui.navigation.model.MainEvent
import com.mani.quotify007.ui.navigation.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val _copyTextEvent = MutableLiveData<Quote>()
    val copyTextEvent: LiveData<Quote> = _copyTextEvent

    private val _shareClickEvent = MutableLiveData<Quote>()
    val shareClickEvent: LiveData<Quote> = _shareClickEvent

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> = _showToast

    private val getQuoteUseCase = (application as QuotifyApp).getQuoteUseCase

    private lateinit var quoteResult: QuoteResult

    init {
        loadQuotesData()
    }

    private fun loadQuotesData() {
        viewModelScope.launch {
            _state.value.isLoading = true
            try {
                quoteResult = getQuoteUseCase.result()
            } catch (e: Exception) {
                _showToast.postValue(e.message)
            } finally {
                _state.value.isLoading = false
            }
            (application as QuotifyApp).quoteDb.favoriteQuoteDao().getAllFavoriteQuotes()
                .catch { e -> e.printStackTrace() }
                .collect { quotes ->
                    _state.value =
                        _state.value.copy(favoriteQuotes = quotes.map { it.toDomainModel() })
                    _state.value = _state.value.copy(
                        quotes = quoteResult.results.map { quote ->
                            if (state.value.favoriteQuotes.any { it._id == quote._id }) {
                                quote.isFavorite = true
                            } else {
                                quote.isFavorite = false
                            }
                            quote
                        }
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

                is MainEvent.CopyText -> _copyTextEvent.postValue(event.quote)
                is MainEvent.ShareClick -> _shareClickEvent.postValue(event.quote)
                is MainEvent.ShowToast -> _showToast.postValue(event.message)
            }
        }
    }

    private fun FavoriteQuoteEntity.toDomainModel() = Quote(id, content = content, author = author, isFavorite = true)

    private fun Quote.toEntity() = FavoriteQuoteEntity(id = _id, content = content, author = author)
}
package com.mani.quotify007.ui.navigation.model

import com.mani.quotify007.domain.model.Quote

data class MainState(
    val quotes: List<Quote> = emptyList(),
    val favoriteQuotes: List<Quote> = emptyList(),
    val randomQuote: Quote? = null,
    var isLoading: Boolean = false
)
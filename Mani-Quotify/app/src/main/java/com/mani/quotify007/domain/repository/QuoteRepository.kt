package com.mani.quotify007.domain.repository

import com.mani.quotify007.domain.model.Quote

interface QuoteRepository {
    fun getQuotes(): List<Quote>
    fun addQuote(quote: Quote)
    fun removeQuote(quote: Quote)
    fun getFavoriteQuotes(): List<Quote>
    fun addFavoriteQuote(quote: Quote)
    fun removeFavoriteQuote(quote: Quote)
}
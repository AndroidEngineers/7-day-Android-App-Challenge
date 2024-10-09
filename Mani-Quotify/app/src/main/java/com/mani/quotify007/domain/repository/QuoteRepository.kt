package com.mani.quotify007.domain.repository

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import kotlinx.coroutines.flow.Flow

    interface QuoteRepository {
    suspend fun getQuoteResult(): QuoteResult
    fun addQuote(quote: Quote)
    fun removeQuote(quote: Quote)
    suspend fun getFavoriteQuotes(): Flow<List<Quote>>
    suspend fun addFavoriteQuote(quote: Quote)
    suspend fun removeFavoriteQuote(quote: Quote)
}
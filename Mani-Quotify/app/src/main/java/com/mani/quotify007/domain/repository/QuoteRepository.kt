package com.mani.quotify007.domain.repository

import com.mani.quotify007.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getQuotes(): List<Quote>
    fun addQuote(quote: Quote)
    fun removeQuote(quote: Quote)
    fun getFavoriteQuotes(): Flow<List<Quote>>
    suspend fun addFavoriteQuote(quote: Quote)
    suspend fun removeFavoriteQuote(quote: Quote)
}
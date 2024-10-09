package com.mani.quotify007.domain.usecase

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.domain.repository.QuoteRepository

class GetQuoteUseCase(private val repository: QuoteRepository) {
    suspend fun result(): QuoteResult = repository.getQuoteResult()
    suspend fun dbQuotes() = repository.getFavoriteQuotes()
    suspend fun addFavoriteQuote(quote: Quote) = repository.addFavoriteQuote(quote)
    suspend fun removeFavoriteQuote(quote: Quote) = repository.removeFavoriteQuote(quote)
    // TODO: Use when static quote data used
    fun execute(): List<Quote> = repository.getQuotes()
}
package com.mani.quotify007.domain.usecase

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.domain.repository.QuoteRepository

class GetQuoteUseCase(private val repository: QuoteRepository) {
    suspend fun result(): QuoteResult = repository.getQuoteResult()
    // TODO: Use when static quote data used
    fun execute(): List<Quote> = repository.getQuotes()
}
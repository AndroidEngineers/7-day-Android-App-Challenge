package com.mani.quotify007.domain.usecase

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.repository.QuoteRepository

class GetQuoteUseCase(private val repository: QuoteRepository) {
    fun execute(): List<Quote> = repository.getQuotes()
}
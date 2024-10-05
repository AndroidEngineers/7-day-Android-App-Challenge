package com.example.quotesapp.domain.usecases

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository

class GetQuote(val quoteRepository: QuoteRepository) {

    operator fun invoke():MutableList<Quote>{
        return quoteRepository.getQuote()
    }
}
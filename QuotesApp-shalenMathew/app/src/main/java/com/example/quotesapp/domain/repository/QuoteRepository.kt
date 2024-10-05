package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.model.Quote

interface QuoteRepository {

    fun getQuote(): MutableList<Quote>

//    fun saveLikedQuote(id: Int)

}
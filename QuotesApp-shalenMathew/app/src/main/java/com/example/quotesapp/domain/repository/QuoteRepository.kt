package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.model.QuoteHome
import com.example.quotesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuote(): Flow<Resource<QuoteHome>>

    fun saveLikedQuote(id: Int)

}
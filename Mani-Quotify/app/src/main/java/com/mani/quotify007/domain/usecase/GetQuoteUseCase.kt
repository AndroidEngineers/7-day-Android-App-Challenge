package com.mani.quotify007.domain.usecase

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.domain.repository.QuoteRepository
import com.mani.quotify007.ui.navigation.model.ResponseResult

class GetQuoteUseCase(private val repository: QuoteRepository) {
    suspend fun result(): ResponseResult<QuoteResult> {
        return try {
            ResponseResult.Success(repository.getQuoteResult())
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }
    suspend fun dbQuotes() = repository.getFavoriteQuotes()
    suspend fun addFavoriteQuote(quote: Quote) = repository.addFavoriteQuote(quote)
    suspend fun removeFavoriteQuote(quote: Quote) = repository.removeFavoriteQuote(quote)
}
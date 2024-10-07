package com.mani.quotify007.data.repository

import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.data.local.FavoriteQuoteEntity
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteRepositoryImpl(private val application: QuotifyApp) : QuoteRepository {

    override suspend fun getQuoteResult(): QuoteResult = application.api.getQuoteResult()

    override fun getQuotes(): List<Quote> = quotesDataList

    //TODO: Future implementation
    override fun addQuote(quote: Quote) {
        quotesDataList.add(quote)
    }

    //TODO: Future implementation
    override fun removeQuote(quote: Quote) {
        quotesDataList.remove(quote)
    }

    override fun getFavoriteQuotes(): Flow<List<Quote>> =
        application.quoteDb.favoriteQuoteDao().getAllFavoriteQuotes().map {entities ->
            entities.map { entity -> entity.toDomainModel() }
        }

    override suspend fun addFavoriteQuote(quote: Quote) {
        application.quoteDb.favoriteQuoteDao().insertFavoriteQuote(quote.toEntity())
    }

    override suspend fun removeFavoriteQuote(quote: Quote) {
        application.quoteDb.favoriteQuoteDao().deleteFavoriteQuote(quote.toEntity())
    }
}

private fun Quote.toEntity() = FavoriteQuoteEntity(id = _id, content = content, author = author)

private fun FavoriteQuoteEntity.toDomainModel() = Quote(id, content = content, author = author)

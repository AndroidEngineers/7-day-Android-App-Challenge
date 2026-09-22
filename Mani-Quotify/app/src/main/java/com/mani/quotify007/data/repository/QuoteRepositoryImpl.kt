package com.mani.quotify007.data.repository

import com.mani.quotify007.data.local.FavoriteQuoteEntity
import com.mani.quotify007.data.local.QuotifyDatabase
import com.mani.quotify007.data.remote.QuoteApiService
import com.mani.quotify007.data.remote.mapper.toResults
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult
import com.mani.quotify007.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteRepositoryImpl(
    private val quoteApiService: QuoteApiService,
    private val quoteDb: QuotifyDatabase
) : QuoteRepository {

    override suspend fun getQuoteResult(): QuoteResult =
        quoteApiService.getQuoteResult().toResults()

    override fun addQuote(quote: Quote) {
        //TODO: add quote to list
    }

    override fun removeQuote(quote: Quote) {
        //TODO: remove quote from list
    }

    override suspend fun getFavoriteQuotes(): Flow<List<Quote>> =
        quoteDb.favoriteQuoteDao().getAllFavoriteQuotes().map {entities ->
            entities.map { entity -> entity.toDomainModel() }
        }

    override suspend fun addFavoriteQuote(quote: Quote) {
        quoteDb.favoriteQuoteDao().insertFavoriteQuote(quote.toEntity())
    }

    override suspend fun removeFavoriteQuote(quote: Quote) {
        quoteDb.favoriteQuoteDao().deleteFavoriteQuote(quote.toEntity())
    }
}

private fun Quote.toEntity() = FavoriteQuoteEntity(id = id, content = content, author = author)

private fun FavoriteQuoteEntity.toDomainModel() =
    Quote(id, content = content, author = author, isFavorite = true)

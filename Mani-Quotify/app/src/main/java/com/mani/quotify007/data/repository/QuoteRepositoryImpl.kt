package com.mani.quotify007.data.repository

import com.mani.quotify007.data.local.FavoriteQuoteDao
import com.mani.quotify007.data.local.FavoriteQuoteEntity
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteRepositoryImpl(private val favoriteQuoteDao: FavoriteQuoteDao) : QuoteRepository {
    private val quotes = mutableListOf(
        Quote(
            1,
            "The greatest glory in living lies not in never falling, " +
                    "but in rising every time we fall.",
            "Nelson Mandela"
        ),
        Quote(
            2,
            "The way to get started is to quit talking and begin doing.",
            "Walt Disney"
        ),
        Quote(
            3,
            "Your time is limited, so don't waste it living someone else's life.",
            "Steve Jobs"
        ),
        Quote(
            4,
            "If life were predictable it would cease to be life, and be without flavor.",
            "Eleanor Roosevelt"
        ),
        Quote(
            5,
            "If you look at what you have in life, you'll always have more. " +
                    "If you look at what you don't have in life, you'll never have enough.",
            "Oprah Winfrey"
        ),
        Quote(
            6,
            "If you set your goals ridiculously high and it's a failure, " +
                    "you will fail above everyone else's success.",
            "James Cameron"
        )
    )

    override fun getQuotes(): List<Quote> = quotes

    override fun addQuote(quote: Quote) {
        quotes.add(quote)
    }

    override fun removeQuote(quote: Quote) {
        quotes.remove(quote)
    }

    override fun getFavoriteQuotes(): Flow<List<Quote>> =
        favoriteQuoteDao.getAllFavoriteQuotes().map {entities ->
            entities.map { entity -> entity.toDomainModel() }
        }

    override suspend fun addFavoriteQuote(quote: Quote) {
        favoriteQuoteDao.insertFavoriteQuote(quote.toEntity())
    }

    override suspend fun removeFavoriteQuote(quote: Quote) {
        favoriteQuoteDao.deleteFavoriteQuote(quote.toEntity())
    }
}

private fun Quote.toEntity() = FavoriteQuoteEntity(id = id, text = text, author = author ?: "")

private fun FavoriteQuoteEntity.toDomainModel() = Quote(id, text, author)

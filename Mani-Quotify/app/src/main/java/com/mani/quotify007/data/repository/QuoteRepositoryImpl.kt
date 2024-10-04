package com.mani.quotify007.data.repository

import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.repository.QuoteRepository

class QuoteRepositoryImpl : QuoteRepository {
    private val quotes = mutableListOf(
        Quote("The greatest glory in living lies not in never falling, but in rising every time we fall."),
        Quote("The way to get started is to quit talking and begin doing."),
        Quote("Your time is limited, so don't waste it living someone else's life."),
        Quote("If life were predictable it would cease to be life, and be without flavor."),
        Quote("If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough."),
        Quote("If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success.")
    )
    private val favoriteQuotes = mutableListOf<Quote>()

    override fun getQuotes(): List<Quote> = quotes

    override fun addQuote(quote: Quote) {
        quotes.add(quote)
    }

    override fun removeQuote(quote: Quote) {
        quotes.remove(quote)
    }

    override fun getFavoriteQuotes(): List<Quote> = favoriteQuotes

    override fun addFavoriteQuote(quote: Quote) {
        favoriteQuotes.add(quote)
    }

    override fun removeFavoriteQuote(quote: Quote) {
        favoriteQuotes.remove(quote)
    }
}
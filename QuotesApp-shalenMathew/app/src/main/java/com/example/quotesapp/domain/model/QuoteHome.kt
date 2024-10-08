package com.example.quotesapp.domain.model


data class QuoteHome (
val quotesList:MutableList<Quote>,
 val quotesOfTheDay:List<Quote>
)
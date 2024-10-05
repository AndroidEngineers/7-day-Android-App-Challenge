package com.example.quotesapp.domain.usecases

data class QuoteUseCase(
    val getQuote: GetQuote,
    val likedQuote: LikedQuote
)

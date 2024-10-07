package com.mani.quotify007.domain.model

data class QuoteResult(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Quote>,
    val totalCount: Int,
    val totalPages: Int
)
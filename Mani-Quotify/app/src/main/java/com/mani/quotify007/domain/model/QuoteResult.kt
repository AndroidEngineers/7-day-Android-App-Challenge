package com.mani.quotify007.domain.model

data class QuoteResult(
    val count: Long,
    val lastItemIndex: Long,
    val page: Long,
    val results: List<Quote>,
    val totalCount: Long,
    val totalPages: Long
)
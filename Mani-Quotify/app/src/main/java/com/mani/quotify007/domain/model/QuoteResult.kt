package com.mani.quotify007.domain.model

data class QuoteResult(
    val count: Long? = null,
    val lastItemIndex: Long? = null,
    val page: Long? = null,
    val results: List<Quote>,
    val totalCount: Long? = null,
    val totalPages: Long? = null
)
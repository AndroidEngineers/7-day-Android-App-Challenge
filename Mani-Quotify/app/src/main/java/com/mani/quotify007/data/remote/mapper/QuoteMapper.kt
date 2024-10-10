package com.mani.quotify007.data.remote.mapper

import com.mani.quotify007.data.remote.model.QuoteNetworkModel
import com.mani.quotify007.domain.model.Quote
import com.mani.quotify007.domain.model.QuoteResult

fun QuoteNetworkModel.toResults() = QuoteResult(
    count = count,
    lastItemIndex = lastItemIndex,
    page = page,
    results = results.map {
        Quote(
            id = it.id,
            author = it.author,
            content = it.content,
            isFavorite = false
        )
    },
    totalCount = totalCount,
    totalPages = totalPages
)

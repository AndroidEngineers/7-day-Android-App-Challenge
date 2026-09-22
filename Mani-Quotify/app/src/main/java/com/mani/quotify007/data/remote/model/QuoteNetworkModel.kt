package com.mani.quotify007.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteNetworkModel (
    val count: Long,
    val totalCount: Long,
    val page: Long,
    val totalPages: Long,
    val lastItemIndex: Long,
    val results: List<QuoteItemNetworkModel>
)

@Serializable
data class QuoteItemNetworkModel (
    @SerialName("_id")
    val id: String,
    val author: String,
    val content: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Long,
    val dateAdded: String,
    val dateModified: String
)
package com.mani.quotify007.domain.model

data class Quote(
    val _id: String,
    val author: String,
    val authorSlug: String? = null,
    val content: String,
    val dateAdded: String? = null,
    val dateModified: String? = null,
    val length: Int? = null,
    val tags: List<String>? = null,
    var isFavorite: Boolean = false
)

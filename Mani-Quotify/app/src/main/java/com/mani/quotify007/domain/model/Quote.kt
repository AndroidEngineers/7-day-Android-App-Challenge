package com.mani.quotify007.domain.model

data class Quote(
    val id: String,
    val content: String,
    val author: String,
    var isFavorite: Boolean = false
)

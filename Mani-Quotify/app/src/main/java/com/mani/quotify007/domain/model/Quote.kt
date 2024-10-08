package com.mani.quotify007.domain.model

data class Quote(
    val id: Int,
    val text: String,
    val author: String? = null,
    var isFavorite: Boolean = false
)

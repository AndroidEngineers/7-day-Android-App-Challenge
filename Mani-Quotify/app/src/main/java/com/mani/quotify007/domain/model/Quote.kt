package com.mani.quotify007.domain.model

data class Quote(val text: String, val author: String? = null, var isFavorite: Boolean = false)

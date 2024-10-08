package com.example.quotesapp.domain.model

data class Quote(
    val quote: String,
    val author:String,
    var liked:Boolean
)
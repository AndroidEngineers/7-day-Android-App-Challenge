package com.example.quotesapp.ui.home_screen

import com.example.quotesapp.domain.model.Quote

data class QuoteState (
    val data:MutableList<Quote> = mutableListOf(),
    val liked:Boolean=false
    )
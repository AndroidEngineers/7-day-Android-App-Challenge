package com.example.quotesapp.ui.home_screen.util

import com.example.quotesapp.domain.model.Quote

data class QuoteState (
    val dataList:MutableList<Quote> = mutableListOf(),
    val qot:Quote? = null,
    val liked:Boolean=false,
    val error:String=""
    )
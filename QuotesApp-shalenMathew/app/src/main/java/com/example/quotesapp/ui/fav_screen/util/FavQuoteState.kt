package com.example.quotesapp.ui.fav_screen.util

import com.example.quotesapp.domain.model.Quote

data class FavQuoteState (
    val favDataList:List<Quote> = emptyList(),
    val liked:Boolean=false,
    val error:String="",
    val isLoading: Boolean =false,
    )
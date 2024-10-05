package com.example.quotesapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.quotesapp.domain.usecases.QuoteUseCase
import com.example.quotesapp.ui.home_screen.QuoteEvent
import com.example.quotesapp.ui.home_screen.QuoteState

class QuoteViewModel(private val quoteUseCase: QuoteUseCase):ViewModel() {

    private val _quoteState = mutableStateOf(QuoteState())
    val quoteState = _quoteState

    init {
        getQuote()
    }


   private fun getQuote(){
        _quoteState.value= _quoteState.value.copy(data=quoteUseCase.getQuote())
    }

    fun onEvent(quoteEvent: QuoteEvent){

        when(quoteEvent){

            QuoteEvent.LIKE -> {Log.d("TAG","LIKE")}
            QuoteEvent.SHARE -> {Log.d("TAG","SHARE")}
        }

    }

}
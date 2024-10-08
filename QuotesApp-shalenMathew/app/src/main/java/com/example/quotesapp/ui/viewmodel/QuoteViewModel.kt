package com.example.quotesapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domain.usecases.QuoteUseCase
import com.example.quotesapp.ui.home_screen.util.QuoteEvent
import com.example.quotesapp.ui.home_screen.util.QuoteState
import com.example.quotesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuoteViewModel @Inject constructor(private val quoteUseCase: QuoteUseCase):ViewModel() {

    private val _quoteState = mutableStateOf(QuoteState())
    val quoteState = _quoteState

    init {
        getQuote()
    }


   private fun getQuote(){

       viewModelScope.launch {

           quoteUseCase.getQuote().collect{it->

               when(it){
                  is Resource.Success->{

                      Log.d("TAG","viewmodel " + it.data!!.quotesList[0].toString())
                      _quoteState.value = _quoteState.value.copy(dataList = it.data.quotesList,
                          qot = it.data.quotesOfTheDay[0])
                  }

                   is Resource.Error -> {
                       Log.d("TAG","From viewmodel - ${it.message}")
                   }
                   is Resource.Loading -> {
                       Log.d("TAG","From viewmodel - Loading")
                   }
               }
           }
       }
    }

    fun onEvent(quoteEvent: QuoteEvent){

        when(quoteEvent){

            is QuoteEvent.Like -> {

            }
            is QuoteEvent.Share -> {Log.d("TAG","SHARE")}
        }

    }

}
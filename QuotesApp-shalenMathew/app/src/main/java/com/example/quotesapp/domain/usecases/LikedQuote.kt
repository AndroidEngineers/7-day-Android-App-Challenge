package com.example.quotesapp.domain.usecases


import com.example.quotesapp.domain.repository.QuoteRepository

class LikedQuote(val quoteRepository: QuoteRepository) {

//    operator fun  invoke(id:Int){
//        quoteRepository.saveLikedQuote(id)
//    }
}
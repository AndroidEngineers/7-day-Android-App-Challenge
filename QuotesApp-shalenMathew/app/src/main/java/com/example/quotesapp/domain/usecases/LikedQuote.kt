package com.example.quotesapp.domain.usecases


import com.example.quotesapp.domain.repository.QuoteRepository
import javax.inject.Inject

class LikedQuote @Inject constructor(val quoteRepository: QuoteRepository) {

//    operator fun  invoke(id:Int){
//        quoteRepository.saveLikedQuote(id)
//    }
}
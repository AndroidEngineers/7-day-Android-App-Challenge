package com.example.quotesapp.data

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository

class QuoteRepositoryImplementation:QuoteRepository {
    override fun getQuote(): MutableList<Quote> {
        return mutableListOf(
            Quote(quote = "Mjisj ijis ocmskcjsc scjsicskcc sck" , author = "Ti shaw",false),
            Quote(quote = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam at sapien eget lacus.", author = "Shalen mathew",false),
            Quote(quote = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ut neque sit amet " + "lectus interdum elementum. Suspendisse vel " , author = "Xing Ping",false))
    }
}
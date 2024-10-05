package com.mani.quotify007

import android.app.Application
import com.mani.quotify007.data.repository.QuoteRepositoryImpl
import com.mani.quotify007.domain.usecase.GetQuoteUseCase

class QuotifyApp: Application() {
    lateinit var getQuoteUseCase: GetQuoteUseCase
    override fun onCreate() {
        super.onCreate()
        val quoteRepository = QuoteRepositoryImpl()
        getQuoteUseCase = GetQuoteUseCase(repository = quoteRepository)
    }
}
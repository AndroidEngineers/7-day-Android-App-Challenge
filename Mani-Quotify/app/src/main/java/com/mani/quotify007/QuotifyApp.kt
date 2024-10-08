package com.mani.quotify007

import android.app.Application
import androidx.room.Room
import com.mani.quotify007.data.local.QuotifyDatabase
import com.mani.quotify007.data.repository.QuoteRepositoryImpl
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.ui.screens.utils.QUOTIFY_DB_NAME

class QuotifyApp: Application() {
    lateinit var quoteDb: QuotifyDatabase
    lateinit var getQuoteUseCase: GetQuoteUseCase
    override fun onCreate() {
        super.onCreate()
        quoteDb = Room.databaseBuilder(
            applicationContext,
            QuotifyDatabase::class.java,
            QUOTIFY_DB_NAME
        ).build()
        val quoteRepository = QuoteRepositoryImpl(quoteDb.favoriteQuoteDao())
        getQuoteUseCase = GetQuoteUseCase(repository = quoteRepository)
    }
}
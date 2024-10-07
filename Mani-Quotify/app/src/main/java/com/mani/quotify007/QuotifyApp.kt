package com.mani.quotify007

import android.app.Application
import androidx.room.Room
import com.mani.quotify007.data.local.QuotifyDatabase
import com.mani.quotify007.data.remote.QuoteApiService
import com.mani.quotify007.data.remote.getSafeOkHttpClient
import com.mani.quotify007.data.repository.QuoteRepositoryImpl
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.ui.screens.utils.QUOTIFY_DB_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.quotable.io/"
class QuotifyApp: Application() {
    lateinit var api: QuoteApiService
    lateinit var quoteDb: QuotifyDatabase
    lateinit var getQuoteUseCase: GetQuoteUseCase
    override fun onCreate() {
        super.onCreate()
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getSafeOkHttpClient(this))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApiService::class.java)

        quoteDb = Room.databaseBuilder(
            applicationContext,
            QuotifyDatabase::class.java,
            QUOTIFY_DB_NAME
        ).build()
        val quoteRepository = QuoteRepositoryImpl(this)
        getQuoteUseCase = GetQuoteUseCase(repository = quoteRepository)
    }
}
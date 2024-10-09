package com.mani.quotify007

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mani.quotify007.data.local.FavoriteQuoteDao
import com.mani.quotify007.data.local.QuotifyDatabase
import com.mani.quotify007.data.remote.QuoteApiService
import com.mani.quotify007.data.remote.getSafeOkHttpClient
import com.mani.quotify007.data.repository.QuoteRepositoryImpl
import com.mani.quotify007.domain.repository.QuoteRepository
import com.mani.quotify007.domain.usecase.GetQuoteUseCase
import com.mani.quotify007.ui.screens.utils.BASE_URL
import com.mani.quotify007.ui.screens.utils.QUOTIFY_DB_NAME
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface QuotiyAppModule {
    val retrofit: Retrofit
    val quoteDb: QuotifyDatabase
    val quoteApiService: QuoteApiService
    val quoteRepository: QuoteRepository
    val quoteUseCase: GetQuoteUseCase
    val quoteFavoriteQuoteDao: FavoriteQuoteDao
}

class QuotiyAppModuleImpl(context: QuotifyApp) : QuotiyAppModule {
    override val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getSafeOkHttpClient(context))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    override val quoteDb: QuotifyDatabase by lazy {
        Room.databaseBuilder(
            context,
            QuotifyDatabase::class.java,
            QUOTIFY_DB_NAME
        ).build()
    }

    override val quoteApiService: QuoteApiService by lazy {
        retrofit.create(QuoteApiService::class.java)
    }
    override val quoteRepository: QuoteRepository by lazy {
        QuoteRepositoryImpl(quoteApiService, quoteDb)
    }
    override val quoteUseCase: GetQuoteUseCase by lazy {
        GetQuoteUseCase(quoteRepository)
    }
    override val quoteFavoriteQuoteDao: FavoriteQuoteDao by lazy {
        quoteDb.favoriteQuoteDao()
    }
}
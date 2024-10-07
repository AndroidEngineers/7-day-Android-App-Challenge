package com.mani.quotify007.data.remote

import com.mani.quotify007.domain.model.QuoteResult
import retrofit2.http.GET

interface QuoteApiService {
    @GET("quotes")
    suspend fun getQuoteResult(): QuoteResult
}
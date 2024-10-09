package com.mani.quotify007.data.remote

import com.mani.quotify007.data.remote.model.QuoteNetworkModel
import retrofit2.http.GET

interface QuoteApiService {
    @GET("quotes")
    suspend fun getQuoteResult(): QuoteNetworkModel
}
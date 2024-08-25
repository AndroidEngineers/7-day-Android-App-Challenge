package com.abhijith.animex.data.network

import com.abhijith.animex.data.network.model.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeXApiService {

    // Returns current seasonal anime
    @GET("seasons/now")
    suspend fun getSeasonalAnime(): AnimeListResponse

    @GET("seasons/now")
    suspend fun getSeasonalAnimeByOffset(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): AnimeListResponse
}
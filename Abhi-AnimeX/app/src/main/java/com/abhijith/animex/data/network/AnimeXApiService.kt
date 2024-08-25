package com.abhijith.animex.data.network

import com.abhijith.animex.data.network.model.AnimeEntity
import retrofit2.Response
import retrofit2.http.GET

interface AnimeXApiService {

    // Returns current seasonal anime
    @GET("/seasons/now")
    suspend fun getSeasonNowAnime(): Response<List<AnimeEntity>>
}
package com.example.anifetch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServices {
    @GET("anime")
    suspend fun getAnimeList(
        @Query("page") page:Int,
        @Query("limit") limit:Int
    ):AnimeResponse
    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: Int): AnimeDetailsResponse

    @GET("anime/{anime_id}/characters")
    suspend fun getAnimeCharacters(@Path("anime_id")animeId: Int):CharactersResponse
}

object RetrofitInstance{
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: APIServices by lazy {
        retrofit.create(APIServices::class.java)
    }
}
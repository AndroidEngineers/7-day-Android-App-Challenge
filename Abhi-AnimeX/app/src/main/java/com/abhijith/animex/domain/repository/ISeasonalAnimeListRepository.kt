package com.abhijith.animex.domain.repository

import com.abhijith.animex.data.network.model.AnimeListResponse
import kotlinx.coroutines.flow.Flow

interface ISeasonalAnimeListRepository {
    suspend fun getSeasonalAnime(): Flow<AnimeListResponse>
    suspend fun getSeasonalAnimeByOffset(page: Int, count: Int): Flow<AnimeListResponse>
}
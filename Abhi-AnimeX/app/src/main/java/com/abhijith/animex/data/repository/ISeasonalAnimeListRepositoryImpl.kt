package com.abhijith.animex.data.repository

import com.abhijith.animex.data.network.AnimeXApiService
import com.abhijith.animex.data.network.model.AnimeListResponse
import com.abhijith.animex.domain.repository.ISeasonalAnimeListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ISeasonalAnimeListRepositoryImpl @Inject constructor(private val apiService: AnimeXApiService) :
    ISeasonalAnimeListRepository {
    override suspend fun getSeasonalAnime(): Flow<AnimeListResponse> {
        return flow {
            val response = apiService.getSeasonalAnime()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSeasonalAnimeByOffset(page: Int, count: Int): Flow<AnimeListResponse> {
        return flow {
            val response = apiService.getSeasonalAnimeByOffset(page, count)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}
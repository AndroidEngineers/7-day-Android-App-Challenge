package com.abhijith.animex.domain.usecases

import com.abhijith.animex.domain.models.AnimeItem
import com.abhijith.animex.domain.models.toDomain
import com.abhijith.animex.domain.repository.ISeasonalAnimeListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSeasonalAnimeListUseCase @Inject constructor(private val repository: ISeasonalAnimeListRepository) {
    suspend operator fun invoke(): Flow<List<AnimeItem>> {
        return repository.getSeasonalAnime()
            .map { response ->
                response.data?.mapNotNull { it?.toDomain() } ?: emptyList()
            }
    }
}
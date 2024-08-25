package com.abhijith.animex.domain.usecases

import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.domain.model.toDomain
import com.abhijith.animex.domain.repository.ISeasonalAnimeListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSeasonalAnimeListByOffsetUseCase @Inject constructor(private val repository: ISeasonalAnimeListRepository) {
    suspend operator fun invoke(page: Int, count: Int): Flow<List<AnimeItem>> {
        return repository.getSeasonalAnimeByOffset(page, count)
            .map { response ->
                response.data?.mapNotNull { it?.toDomain() } ?: emptyList()
            }
    }
}
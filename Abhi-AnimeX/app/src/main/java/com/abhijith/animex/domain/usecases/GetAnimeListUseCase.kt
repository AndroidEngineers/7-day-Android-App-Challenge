package com.abhijith.animex.domain.usecases

import com.abhijith.animex.data.model.AnimeEntity
import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.domain.model.toDomain

class GetAnimeListUseCase {
    private val animeEntityList = List(10) { generateValidAnime() }

    suspend operator fun invoke(): List<AnimeItem> {
        // map to repository later

        return animeEntityList.map { it.toDomain() }
    }

    private fun generateValidAnime(): AnimeEntity {
        val animeEntity = AnimeEntity()

        val cleanedTitle = animeEntity.title.replace("\\", "").replace("\"", "")
        val cleanedYear = animeEntity.year.ifBlank {
            "-"
        }
        val cleanedRating = animeEntity.rating.ifBlank {
            "-"
        }
        val cleanedRank = animeEntity.rank.ifBlank {
            "-"
        }
        val cleanedScore = animeEntity.score.ifBlank {
            "-"
        }

        return animeEntity.copy(
            title = cleanedTitle,
            year = cleanedYear,
            rating = cleanedRating,
            rank = cleanedRank,
            score = cleanedScore,
        )
    }
}
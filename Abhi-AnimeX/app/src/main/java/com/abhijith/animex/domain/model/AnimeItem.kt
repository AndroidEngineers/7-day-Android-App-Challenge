package com.abhijith.animex.domain.model

import com.abhijith.animex.data.network.model.AnimeEntity

data class AnimeItem(
    val title: String,
    val source: String,
    val yearAndJapaneseName: String,
    val rating: String,
    val rank: String,
    val score: String,
    val synopsis: String,
    val genres: List<String>,
    val imageUrl: String,
    val youtubeId: String
)


fun AnimeEntity.toDomain(): AnimeItem {
    return AnimeItem(
        title = title,
        source = source,
        yearAndJapaneseName = when {
            year.isNotBlank() && japaneseName.isNotBlank() -> "$year | $japaneseName"
            year.isNotBlank() -> year
            japaneseName.isNotBlank() -> japaneseName
            else -> "-"
        },
        rating = rating,
        rank = rank,
        score = score,
        synopsis = synopsis,
        genres = genres,
        imageUrl = imageUrl,
        youtubeId = youtubeId
    )
}
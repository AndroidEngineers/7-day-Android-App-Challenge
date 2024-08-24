package com.abhijith.animex.domain.model

import com.abhijith.animex.data.model.AnimeEntity

data class AnimeItem(
    val title: String,
    val source: String,
    val year: String,
    val japaneseName: String,
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
        year = year,
        japaneseName = japaneseName,
        rating = rating,
        rank = rank,
        score = score,
        synopsis = synopsis,
        genres = genres,
        imageUrl = imageUrl,
        youtubeId = youtubeId
    )
}
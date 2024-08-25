package com.abhijith.animex.domain.models

import com.abhijith.animex.data.network.model.AnimeDto

data class AnimeItem(
    val title: String,
    val source: String,
    val yearAndJapaneseName: String,
    val rating: String,
    val rank: String,
    val score: String,
    val popularity: String,
    val synopsis: String,
    val genres: List<String>,
    val imageUrl: String,
    val youtubeId: String
)


fun AnimeDto.toDomain(): AnimeItem {
    val formattedYearAndJapaneseName = when {
        year != null && titleJapanese?.isNotBlank() == true -> "$year | $titleJapanese"
        year != null -> year.toString()
        titleJapanese?.isNotBlank() == true -> titleJapanese
        else -> "-"
    }

    return AnimeItem(
        title = title.orEmpty(),
        source = source.orEmpty(),
        yearAndJapaneseName = formattedYearAndJapaneseName,
        rating = rating.orEmpty(),
        rank = rank?.toString().orEmpty(),
        score = score?.toString().orEmpty(),
        popularity = popularity?.toString().orEmpty(),
        synopsis = synopsis.orEmpty(),
        genres = genres?.mapNotNull { it?.name }.orEmpty(),
        imageUrl = images?.webp?.imageUrl.orEmpty(),
        youtubeId = trailer?.youtubeId.orEmpty()
    )
}
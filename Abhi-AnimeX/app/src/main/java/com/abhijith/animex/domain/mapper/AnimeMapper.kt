package com.abhijith.animex.domain.mapper

import com.abhijith.animex.data.model.AnimeEntity
import com.abhijith.animex.domain.model.AnimeItem

object AnimeMapper {
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
}
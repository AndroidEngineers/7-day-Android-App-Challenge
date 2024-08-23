package com.abhijith.animex.domain.model

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

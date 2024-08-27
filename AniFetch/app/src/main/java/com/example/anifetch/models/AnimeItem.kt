package com.example.anifetch.models


data class AnimeItem(
    val mal_id :Int,
    val title: String,
    val synopsis: String?,
    val images: Images
)

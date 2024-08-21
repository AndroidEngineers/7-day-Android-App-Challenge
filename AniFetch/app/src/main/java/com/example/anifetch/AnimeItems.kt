package com.example.anifetch

import com.google.gson.annotations.SerializedName


data class AnimeItems(
    val mal_id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("synopsis")
    val synopsis : String?,
    @SerializedName("images")
    val images: AnimeImages
)

data class AnimeResponse(
    @SerializedName("data")
    val data :List<AnimeItems>
)

data class AnimeImages(
    @SerializedName("jpg")
    val jpg: ImageUrl
)

data class ImageUrl(
    @SerializedName("image_url")
    val image_url: String
)

data class AnimeDetailsResponse(
    val mal_Id:Int,
    val title: String,
    val synopsis : String?,
    val images: AnimeImages
)

data class Character(
    val mal_id: Int,
    val name: String,
    val role: String,
    val images: AnimeImages
)
data class CharactersResponse(
    val data: List<Character>
)


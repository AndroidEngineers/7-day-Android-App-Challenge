package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailerDto(

    @SerializedName("images")
    val images: AnimeImagesDto? = null,

    @SerializedName("embed_url")
    val embedUrl: String? = null,

    @SerializedName("youtube_id")
    val youtubeId: String? = null,

    @SerializedName("url")
    val url: String? = null
)

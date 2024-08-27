package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class AnimeImagesDto(

    @SerializedName("jpg")
    val jpg: Jpg? = null,

    @SerializedName("webp")
    val webp: Webp? = null,

    @SerializedName("large_image_url")
    val largeImageUrl: String? = null,

    @SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,

    @SerializedName("medium_image_url")
    val mediumImageUrl: String? = null,

    @SerializedName("maximum_image_url")
    val maximumImageUrl: String? = null
)

data class Jpg(

    @SerializedName("large_image_url")
    val largeImageUrl: String? = null,

    @SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null
)

data class Webp(

    @SerializedName("large_image_url")
    val largeImageUrl: String? = null,

    @SerializedName("small_image_url")
    val smallImageUrl: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null
)
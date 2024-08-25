package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class GenresDto(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("mal_id")
    val malId: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("url")
    val url: String? = null
)
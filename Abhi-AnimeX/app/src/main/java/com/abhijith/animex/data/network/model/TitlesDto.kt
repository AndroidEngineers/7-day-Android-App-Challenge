package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName


data class TitlesDto(

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("title")
    val title: String? = null
)
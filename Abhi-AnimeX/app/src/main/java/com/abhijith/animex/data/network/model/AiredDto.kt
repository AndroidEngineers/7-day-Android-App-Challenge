package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class AiredDto(

    @SerializedName("string")
    val airedInfo: String? = null,

    @SerializedName("from")
    val from: String? = null,

    @SerializedName("to")
    val to: Any? = null
)

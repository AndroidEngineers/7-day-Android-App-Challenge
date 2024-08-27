package com.abhijith.animex.data.network.model

import com.google.gson.annotations.SerializedName

data class BroadcastDto(

    @SerializedName("string")
    val string: String? = null,

    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("time")
    val time: String? = null,

    @SerializedName("day")
    val day: String? = null
)
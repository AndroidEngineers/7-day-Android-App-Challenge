package com.example.reciperoulette.domain.model

import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("localizedName")
    val localizedName: String = "",
    @SerializedName("image")
    val image: String = "",
)

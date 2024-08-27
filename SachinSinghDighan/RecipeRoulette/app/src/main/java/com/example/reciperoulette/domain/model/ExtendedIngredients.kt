package com.example.reciperoulette.domain.model

import com.google.gson.annotations.SerializedName

data class ExtendedIngredients(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("aisle")
    val aisle: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("consistency")
    val consistency: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("nameClean")
    val nameClean: String = "",
    @SerializedName("original")
    val original: String = "",
    @SerializedName("originalName")
    val originalName: String = "",
    @SerializedName("amount")
    val amount: Double = 0.0,
    @SerializedName("unit")
    val unit: String = "",
)

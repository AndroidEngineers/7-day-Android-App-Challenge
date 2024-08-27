package com.android.engineer.mealmate.data.remote.model.response

import com.squareup.moshi.Json

data class NutrientsResponseItem(
    @Json(name = "calories")
    val calories: Int,

    @Json(name = "carbs")
    val carbs: String,

    @Json(name = "fat")
    val fat: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "image")
    val image: String,

    @Json(name = "imageType")
    val imageType: String,

    @Json(name = "protein")
    val protein: String,

    @Json(name = "title")
    val title: String
)
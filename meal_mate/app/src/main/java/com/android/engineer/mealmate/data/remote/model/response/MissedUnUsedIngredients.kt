package com.android.engineer.mealmate.data.remote.model.response

import com.squareup.moshi.Json

data class MissedUnUsedIngredients(
    @Json(name = "id")
    val id: Int,

    @Json(name = "image")
    val image: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "original")
    val original: String,

    @Json(name = "amount")
    val amount: Double
)

package com.android.engineer.mealmate.data.remote.model.response

import com.squareup.moshi.Json

data class UsedIngredient(
    @Json(name = "aisle")
    val aisle: String,

    @Json(name = "amount")
    val amount: Double,

    @Json(name = "id")
    val id: Int,

    @Json(name = "image")
    val image: String,

    @Json(name = "meta")
    val meta: List<Any>,

    @Json(name = "name")
    val name: String,

    @Json(name = "original")
    val original: String,

    @Json(name = "originalName")
    val originalName: String,

    @Json(name = "unit")
    val unit: String,

    @Json(name = "unitLong")
    val unitLong: String,

    @Json(name = "unitShort")
    val unitShort: String
)
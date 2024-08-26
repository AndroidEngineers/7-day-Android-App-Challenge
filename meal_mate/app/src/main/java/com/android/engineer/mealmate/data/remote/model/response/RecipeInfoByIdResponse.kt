package com.android.engineer.mealmate.data.remote.model.response

import com.squareup.moshi.Json

data class RecipeInfoByIdResponse(

    @Json(name = "id")
    val id: Int,

    @Json(name = "sourceName")
    val sourceName: String,

    @Json(name = "sourceUrl")
    val sourceUrl: String,

    @Json(name = "spoonacularSourceUrl")
    val spoonacularSourceUrl: String,

    @Json(name = "title")
    val title: String
)
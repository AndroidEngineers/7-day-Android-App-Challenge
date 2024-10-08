package com.android.engineer.mealmate.data.remote.model.response

import com.squareup.moshi.Json

data class IngredientsResponseItem(
    @Json(name = "id")
    val id: Int,

    @Json(name = "image")
    val image: String,

    @Json(name = "imageType")
    val imageType: String,

    @Json(name = "likes")
    val likes: Int,

    @Json(name = "missedIngredientCount")
    val missedIngredientCount: Int,

    @Json(name = "missedIngredients")
    val missedIngredients: List<MissedIngredient>,

    @Json(name = "title")
    val title: String,

    @Json(name = "unusedIngredients")
    val unusedIngredients: List<UnusedIngredient>,

    @Json(name = "usedIngredientCount")
    val usedIngredientCount: Int,

    @Json(name = "usedIngredients")
    val usedIngredients: List<UsedIngredient>
)
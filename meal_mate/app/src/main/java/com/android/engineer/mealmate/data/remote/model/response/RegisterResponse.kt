package com.android.engineer.mealmate.data.remote.model.response

import com.squareup.moshi.Json

data class RegisterResponse(

    @Json(name = "status")
    val status: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "spoonacularPassword")
    val spoonacularPassword: String,

    @Json(name = "hash")
    val hash: String
)

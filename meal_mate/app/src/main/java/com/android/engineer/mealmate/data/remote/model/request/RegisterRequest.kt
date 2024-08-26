package com.android.engineer.mealmate.data.remote.model.request

import com.squareup.moshi.Json

data class RegisterRequest(
    @Json(name = "username")
    val username: String,

    @Json(name = "firstName")
    val firstName: String,

    @Json(name = "lastName")
    val lastName: String,

    @Json(name = "email")
    val email: String
)

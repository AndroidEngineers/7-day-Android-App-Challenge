package com.example.reciperoulette.domain.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("step")
    val step: String = "",
    @SerializedName("ingredients")
    val ingredients:List<Ingredient> = listOf(),
    @SerializedName("equipment")
    val equipment:List<Equipment> = listOf(),
)

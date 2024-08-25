package com.example.reciperoulette.domain.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class AnalyzedInstruction(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("steps")
    val steps: List<Step> = listOf(),
)

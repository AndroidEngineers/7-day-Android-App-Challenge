package com.example.reciperoulette.model

data class Equipment(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String,
    val temperature: Temperature
)
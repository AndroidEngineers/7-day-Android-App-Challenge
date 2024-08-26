package com.android.engineer.mealmate.data.remote.model.response

data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatche>
)
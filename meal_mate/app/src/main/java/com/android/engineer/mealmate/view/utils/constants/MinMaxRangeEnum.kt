package com.android.engineer.mealmate.view.utils.constants

enum class MinMaxRangeEnum(val minMaxRange: ClosedFloatingPointRange<Float>) {
    MIN_MAX_CARBS(minMaxRange = 10f..100f),
    MIN_MAX_PROTEIN(minMaxRange = 10f..100f),
    MIN_MAX_KCAL(minMaxRange = 50f..800f),
    MIN_MAX_FAT(minMaxRange =  1f..100f)
}
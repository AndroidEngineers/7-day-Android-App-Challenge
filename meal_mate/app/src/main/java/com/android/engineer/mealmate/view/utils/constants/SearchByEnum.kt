package com.android.engineer.mealmate.view.utils.constants

import com.android.engineer.mealmate.view.features.home.BY_INGREDIENTS
import com.android.engineer.mealmate.view.features.home.BY_NUTRIENTS

enum class SearchByEnum(name: String) {
    NUTRIENTS(name = BY_NUTRIENTS),
    INGREDIENTS(name = BY_INGREDIENTS)
}
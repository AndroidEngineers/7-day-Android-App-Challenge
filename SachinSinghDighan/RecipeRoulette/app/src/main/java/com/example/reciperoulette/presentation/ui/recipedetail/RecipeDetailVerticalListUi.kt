package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.lazy.LazyListScope

fun LazyListScope.RecipeDetailContent() {
    items(5) {
        RecipeDetailElement(
            name = it.toString(),
        )
    }
}
package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.example.reciperoulette.domain.model.AnalyzedInstruction

fun LazyListScope.RecipeDetailContent(analyzedInstructions: List<AnalyzedInstruction>) {
    items(analyzedInstructions) { instructions ->
        this@RecipeDetailContent.RecipeDetailElement(
            instructions.steps
        )
    }
}
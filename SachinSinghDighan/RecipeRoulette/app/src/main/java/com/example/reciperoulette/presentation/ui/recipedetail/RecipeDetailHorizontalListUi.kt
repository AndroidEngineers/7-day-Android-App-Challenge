package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.domain.model.ExtendedIngredients


@Composable
fun HorizontalList(extendedIngredients: List<ExtendedIngredients>, modifier: Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(top = 24.dp)
    ) {
        items(extendedIngredients) { ingredients ->
            IngredientElement(ingredients)
        }

    }
}

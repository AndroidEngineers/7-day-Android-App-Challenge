package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme


@Composable
fun HorizontalList(modifier: Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(top = 24.dp)
    ) {
        items(10) {
            Intgradientelement(modifier)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalListPreview() {
    RecipeRouletteTheme {
        HorizontalList(Modifier)
    }
}
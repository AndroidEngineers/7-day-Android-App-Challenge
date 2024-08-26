package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.domain.model.Step


fun LazyListScope.RecipeDetailElement(steps: List<Step>, modifier: Modifier = Modifier) {
    items(steps) {step ->
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            modifier = modifier.padding(vertical = 4.dp, horizontal = 2.dp)
        ) {
            CardDetailContent(step)
        }
    }

}
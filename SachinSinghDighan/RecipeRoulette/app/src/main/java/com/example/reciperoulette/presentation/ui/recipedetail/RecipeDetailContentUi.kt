package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.presentation.ui.commonui.RecipeImage
import com.example.reciperoulette.presentation.ui.commonui.ScreenContentText
import com.example.reciperoulette.presentation.ui.commonui.ScreenSubTitleText
import com.example.reciperoulette.presentation.ui.commonui.ScreenTitleText
import com.example.reciperoulette.R
import com.example.reciperoulette.domain.model.Recipe
import de.charlex.compose.material3.HtmlText

@Composable
fun RecipeDetail(recipeDetail: Recipe) {

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        item {
            RecipeImage(recipeDetail.image)
            ScreenTitleText(
                recipeDetail.title,
                Modifier
                    .padding(top = 16.dp)
            )
            Row(modifier = Modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(stringResource(id = R.string.ready_in_minute), modifier = Modifier.weight(4f))
                ScreenSubTitleText(": ${recipeDetail.readyInMinutes}", modifier = Modifier.weight(1f))

            }

            Row(modifier = Modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(stringResource(id = R.string.healt_score), modifier = Modifier.weight(4f))
                ScreenSubTitleText(": ${recipeDetail.healthScore}", modifier = Modifier.weight(1f))

            }

            Row(modifier = Modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(stringResource(id = R.string.price_per_serving), modifier = Modifier.weight(4f))
                ScreenSubTitleText(": ${recipeDetail.pricePerServing}", modifier = Modifier.weight(1f))

            }
        }

        item {
            ScreenTitleText(
                stringResource(id = R.string.extending_ingredients),
                Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
        }


        item {
            HorizontalList( recipeDetail.extendedIngredients, Modifier)
        }


        item {
            ScreenTitleText(
                stringResource(id = R.string.summary),
                Modifier
                    .padding(top = 16.dp)
            )

            ScreenContentText(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = HtmlText(text = recipeDetail.summary).toString(),
            )
        }

        item {
            ScreenTitleText(
                stringResource(id = R.string.analyzed_instructions),
                Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }


        RecipeDetailContent(recipeDetail.analyzedInstructions)

    }
}

package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.make_food.ui.commonui.RecipeImage
import com.example.make_food.ui.commonui.ScreenContentText
import com.example.make_food.ui.commonui.ScreenSubTitleText
import com.example.make_food.ui.commonui.ScreenTitleText
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun RecipeDetail(name: String, innerPadding: PaddingValues) {

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        item {
            RecipeImage()
            ScreenTitleText(
                name,
                Modifier
                    .padding(top = 16.dp)
            )
            Row(modifier = Modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(name, modifier = Modifier.weight(4f))
                ScreenSubTitleText(": $name", modifier = Modifier.weight(1f))

            }

            Row(modifier = Modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(name, modifier = Modifier.weight(4f))
                ScreenSubTitleText(": $name", modifier = Modifier.weight(1f))

            }

            Row(modifier = Modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(name, modifier = Modifier.weight(4f))
                ScreenSubTitleText(": $name", modifier = Modifier.weight(1f))

            }
        }

        item {
            ScreenTitleText(
                name,
                Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
        }


        item {
            HorizontalList(Modifier)
        }


        item {
            ScreenTitleText(
                name,
                Modifier
                    .padding(top = 16.dp)
            )

            ScreenContentText(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. "
                        ).repeat(10),
            )
        }

        item {
            ScreenTitleText(
                name,
                Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }

        RecipeDetailContent()

    }
}


@Preview(showBackground = true)
@Composable
fun RecipeDetailPreview() {
    RecipeRouletteTheme {
        RecipeDetail("Android", innerPadding = PaddingValues(8.dp))
    }
}
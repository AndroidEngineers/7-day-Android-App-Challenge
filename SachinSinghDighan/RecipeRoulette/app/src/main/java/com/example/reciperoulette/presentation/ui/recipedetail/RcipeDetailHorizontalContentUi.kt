package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.make_food.ui.commonui.HorizontalListImage
import com.example.make_food.ui.commonui.HorizontalListTitleText
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun Intgradientelement(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalListImage()
        HorizontalListTitleText(
            stringResource(R.string.app_name),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun IntgradientelementPreview() {
    RecipeRouletteTheme {
        Intgradientelement(Modifier)
    }
}









package com.example.reciperoulette.presentation.ui.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.presentation.ui.commonui.HorizontalListImage
import com.example.reciperoulette.presentation.ui.commonui.HorizontalListTitleText
import com.example.reciperoulette.domain.model.Equipment
import com.example.reciperoulette.domain.model.ExtendedIngredients
import com.example.reciperoulette.domain.model.Ingredient
import com.example.reciperoulette.presentation.util.AppConstant

@Composable
fun IngredientElement(
    ingredients: ExtendedIngredients,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalListImage("${AppConstant.IMAGE_URL}/${ingredients.image}")
        HorizontalListTitleText(
            ingredients.name,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
        )
    }
}


@Composable
fun StepIngredientElement(
    ingredients: Ingredient,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalListImage("${AppConstant.IMAGE_URL}/${ingredients.image}")
        HorizontalListTitleText(
            ingredients.name,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
        )
    }
}

@Composable
fun StepEquipmentElement(
    ingredients: Equipment,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalListImage(ingredients.image)
        HorizontalListTitleText(
            ingredients.name,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
        )
    }
}










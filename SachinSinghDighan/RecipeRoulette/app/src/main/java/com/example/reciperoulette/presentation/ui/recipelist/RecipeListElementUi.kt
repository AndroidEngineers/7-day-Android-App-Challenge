package com.example.reciperoulette.presentation.ui.recipelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.reciperoulette.R
import com.example.reciperoulette.domain.model.Recipe

@Composable
fun RecipeListElement(
    recipes: List<Recipe>,
    navController: NavHostController,
    onRecipeClick: (recipeId: Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .background(color = colorResource(id = R.color.white))
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        items(recipes) { name ->
            CardContent(name, navController, onRecipeClick)
        }
    }
}


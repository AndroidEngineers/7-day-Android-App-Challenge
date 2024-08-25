package com.example.reciperoulette.presentation.ui.recipelist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun RecipeListElement(
    navController: NavHostController,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(navController, name)
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListElementPreview() {
    RecipeRouletteTheme {
        RecipeListElement(
            navController = rememberNavController(),
            stringResource(id = R.string.recipe_list)
        )
    }
}
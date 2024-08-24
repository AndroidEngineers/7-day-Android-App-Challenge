package com.example.reciperoulette.presentation.ui.recipelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.make_food.ui.commonui.AppBar
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun RecipeListScreen(navController: NavHostController, names: List<String>) {
    Scaffold(topBar = { AppBar(stringResource(id = R.string.recipe_list)) },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                LazyColumn {
                    items(names) { name ->
                        RecipeListElement(
                            navController,
                            name = name,
                        )
                    }
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview() {
    RecipeRouletteTheme {
        RecipeListScreen(navController = rememberNavController(), List(1000) { "$it" })
    }
}


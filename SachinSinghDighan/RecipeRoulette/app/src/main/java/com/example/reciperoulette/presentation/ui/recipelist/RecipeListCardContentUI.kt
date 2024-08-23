package com.example.reciperoulette.presentation.ui.recipelist

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.make_food.ui.commonui.ScreenContentText
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.navigation.Screen
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun CardContent(navController: NavHostController, name: String) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        border = BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.outline
        ),
        shape = MaterialTheme.shapes.medium
    ) {

        Column {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .clickable(onClick = {
                        navController.navigate(Screen.RecipeDetail.route)
                    })
            )
            {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(80.dp)
                    )

                }

                ScreenContentText(
                    text = name,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(24.dp)
                        .weight(1f)
                )

                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded.value) stringResource(R.string.show_less) else stringResource(
                            R.string.show_more
                        )
                    )
                }
            }
            Column {
                if (expanded.value) {
                    ScreenContentText(
                        modifier = Modifier
                            .padding(12.dp),
                        text = (stringResource(id = R.string.demo_content_text)).repeat(4),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardContentPreview() {
    RecipeRouletteTheme {
        CardContent(
            navController = rememberNavController(),
            stringResource(id = R.string.recipe_list)
        )
    }
}
package com.example.reciperoulette.presentation.ui.recipelist

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.reciperoulette.presentation.ui.commonui.ScreenContentText
import com.example.reciperoulette.presentation.ui.commonui.ScreenTitleText
import com.example.reciperoulette.R
import com.example.reciperoulette.domain.model.Recipe
import com.example.reciperoulette.presentation.ui.navigation.Route
import de.charlex.compose.material3.HtmlText

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardContent(
    recipe: Recipe,
    navController: NavHostController,
    onRecipeClick: (recipeId: Int) -> Unit,
) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        border = BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.outline
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
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
                        onRecipeClick(recipe.id)
                        navController.navigate(Route.RecipeDetailScreen.route)
                    })
            )
            {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    GlideImage(
                        model = recipe.image,
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.FillBounds
                    )

                }

                ScreenContentText(
                    text = recipe.title,
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

            if (expanded.value) {
                Column(modifier = Modifier.padding(12.dp)) {
                    ScreenTitleText(stringResource(id = R.string.analyzed_instructions), modifier = Modifier.padding(bottom = 18.dp))
                    ScreenContentText(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                        text = HtmlText(text = recipe.instructions).toString(),
                    )
                }
            }
        }
    }
}
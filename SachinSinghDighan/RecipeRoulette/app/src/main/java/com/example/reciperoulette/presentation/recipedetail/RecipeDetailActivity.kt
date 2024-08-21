package com.example.reciperoulette.presentation.recipedetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.make_food.ui.commonui.AppBar
import com.example.make_food.ui.commonui.HorizontalListImage
import com.example.make_food.ui.commonui.HorizontalListTitleText
import com.example.make_food.ui.commonui.RecipeImage
import com.example.make_food.ui.commonui.ScreenContentText
import com.example.make_food.ui.commonui.ScreenSubTitleText
import com.example.make_food.ui.commonui.ScreenTitleText
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.recipedetail.ui.theme.RecipeRouletteTheme

class RecipeDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeDetailScreen()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RecipeDetailScreen() {
    RecipeRouletteTheme {
        Scaffold(

            topBar = { AppBar("RecipeDetail") }
        ) {  _ ->
            RecipeDetail(
                name = "Android",
                modifier = Modifier.padding()
            )
        }
    }
}

@Composable
fun RecipeDetail(name: String, modifier: Modifier = Modifier, names: List<String> = List(5) { "$it" }) {

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        item{
            RecipeImage()
            ScreenTitleText(
                name,
                modifier
                    .padding(top = 16.dp)
            )
            Row(modifier = modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(name, modifier = modifier.weight(4f))
                ScreenSubTitleText(": $name", modifier = modifier.weight(1f))

            }

            Row(modifier = modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(name, modifier = modifier.weight(4f))
                ScreenSubTitleText(": $name", modifier = modifier.weight(1f))

            }

            Row(modifier = modifier.padding(top = 24.dp)) {
                ScreenSubTitleText(name, modifier = modifier.weight(4f))
                ScreenSubTitleText(": $name", modifier = modifier.weight(1f))

            }
        }

        item {
            ScreenTitleText(
                name,
                modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
        }


        item {
            HorizontalList(modifier)
        }


        item {
            ScreenTitleText(
                name,
                modifier
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
                modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }

        RecipeDetailContent()

    }
}

fun LazyListScope.RecipeDetailContent()
{
    items(5) {
        RecipeDetailElement(
            name = it.toString(),
        )
    }
}

@Composable
fun RecipeDetailElement(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        CardDetailContent(name)
    }
}

@Composable
private fun CardDetailContent(name: String, modifier: Modifier = Modifier,content: List<String> = List(10) { "$it" }) {

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
                    .padding(start = 4.dp, end = 4.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            )
            {

                ScreenContentText(
                    text = name,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
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

                Column(modifier = Modifier
                    .padding(12.dp)) {

                    ScreenTitleText(
                        "Ingredients",
                        modifier
                            .padding(top = 16.dp, bottom = 12.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = modifier.padding(top = 24.dp, bottom = 12.dp)
                    ) {
                        items(content){
                            Intgradientelement(modifier)
                        }

                    }


                    ScreenTitleText(
                        "Equipements",
                        modifier
                            .padding(top = 16.dp, bottom = 12.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = modifier.padding(top = 24.dp, bottom = 12.dp)
                    ) {
                        items(content){
                            Intgradientelement(modifier)
                        }

                    }

                }
            }
        }
    }
}



@Composable
fun HorizontalList(modifier: Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(top = 24.dp)
    ) {
        items(10){
            Intgradientelement(modifier)
        }

    }
}


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

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeRouletteTheme {
        RecipeDetailScreen()
    }
}

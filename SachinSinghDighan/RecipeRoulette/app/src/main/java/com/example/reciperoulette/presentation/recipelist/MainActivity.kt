package com.example.reciperoulette.presentation.recipelist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.make_food.ui.commonui.AppBar
import com.example.make_food.ui.commonui.ScreenContentText
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.recipedetail.RecipeDetailActivity
import com.example.reciperoulette.presentation.recipelist.ui.theme.RecipeRouletteTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(topBar = { AppBar("RecipeList") }) {
                RecipeRouletteTheme {
                    RecipeList(modifier = Modifier.fillMaxSize(), it)
                }
            }
        }
    }
}


@Composable
fun RecipeList(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    var shouldShowOnBoardingScreen by rememberSaveable {
        mutableStateOf(true)
    }

    Surface(modifier.padding(top = paddingValues.calculateTopPadding()), color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnBoardingScreen) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnBoardingScreen = false })
        } else {
            RecipeListContent()
        }
    }
}


@Composable
fun RecipeListContent(names: List<String> = List(1000) { "$it" }) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            LazyColumn {
                items(names) { name ->
                    RecipeListElement(
                        name = name,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
}


@Composable
fun RecipeListElement(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {

    val mContext = LocalContext.current

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
                        mContext.startActivity(Intent(mContext, RecipeDetailActivity::class.java))
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
                    ScreenContentText(modifier = Modifier
                        .padding(12.dp),
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
        }
    }
}


@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScreenContentText(text = "Welcome to the Basics Codelab!")
        Button(
            modifier = modifier.padding(24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    RecipeRouletteTheme {
        AppBar("RecipeList")
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    RecipeRouletteTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipeRouletteTheme {
        RecipeListElement("Android")
    }
}
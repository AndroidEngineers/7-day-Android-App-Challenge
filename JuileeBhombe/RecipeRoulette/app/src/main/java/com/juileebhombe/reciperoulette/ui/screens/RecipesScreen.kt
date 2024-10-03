package com.juileebhombe.reciperoulette.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juileebhombe.reciperoulette.R
import com.juileebhombe.reciperoulette.ui.theme.RecipeRouletteTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesScreen() {
    LazyColumn {
        stickyHeader {
            RecipesTab()

        }
        items(50) {
            RecipeCard()
        }
    }
}


@Composable
fun RecipesTab() {
    val tabs = listOf("ALL", "VEG", "VEGAN", "NON-VEG")

    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    TabRow(selectedTabIndex = selectedIndex.intValue) {

        tabs.forEachIndexed { index, s ->
            Tab(
                selected = selectedIndex.intValue == index,
                onClick = { selectedIndex.intValue = index }) {
                Text(text = s)
            }
        }
    }
}


@Composable
fun RecipeCard() {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(32.dp))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Background Image", contentScale = ContentScale.FillBounds
        )

        Text(
            text = "Recipe Name", modifier = Modifier.padding(15.dp), fontWeight = FontWeight(900),
            fontSize = 20.sp,
            letterSpacing = (1).sp,
            fontFamily = FontFamily.SansSerif,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RecipesScreenPreview() {
    RecipeRouletteTheme {
        RecipesScreen()
    }
}
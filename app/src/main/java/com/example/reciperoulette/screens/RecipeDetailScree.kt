package com.example.reciperoulette.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reciperoulette.R

@Preview
@Composable
fun RecipeDetailScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF0D0D0D))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            RecipeDetailToolBar()
            HeadingUi()
            Image(
                painter = painterResource(id = R.drawable.recipe),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            HeadingUi(headingName = "Gradiant")
            GradiantListScreen()
            HeadingUi(headingName = "Summary")
            SummaryScreen()
            HeadingUi(headingName = "Instruction")
            InstructionScreen()
        }
    }
}

@Preview
@Composable
private fun RecipeDetailToolBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF0D0D0D))
            .drawBehind {
                val borderSize = 1.dp.toPx()
                val x = size.width
                val y = size.height
                drawLine(
                    color = Color(0xFF333333),
                    start = Offset(0f, y),
                    end = Offset(x, y),
                    strokeWidth = borderSize
                )
            }
            .padding(16.dp)
    ) {
        Row(
            modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.White)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Recipe Roulette",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFEEEEEE),
                )
            )
        }
    }
}

@Preview
@Composable
private fun HeadingUi(modifier: Modifier = Modifier, headingName: String = "Recipe Name") {
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = headingName,
        style = MaterialTheme.typography.headlineMedium,
        color = Color(0xFFFEFEFE)
    )
}

@Preview
@Composable
private fun GradiantListItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.recipe),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "gradiant Name",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 10.4.sp,
                    fontWeight = FontWeight(300),
                    color = Color(0xFFFEFEFE)
                )
            )

        }

    }
}

@Preview
@Composable
private fun GradiantListScreen(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(10) {
            GradiantListItem()
        }
    }
}

@Preview
@Composable
private fun SummaryScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Madeleine Thien is a Canadian writer whose work explores the trans-cultural world of Asian art, politics, and family life within Canada’s diasporic Asian Communities. She was born in 1974 to a Malaysian Chinese father and a Hong Kong Chinese mother. Thien studied contemporary dance but switched to creative writing as an undergraduate in college. She earned her MFA in writing from the University of British Columbia.",
        color = Color.White,
        modifier = modifier.padding(horizontal = 16.dp)
    )
}

@Preview
@Composable
private fun InstructionScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Madeleine Thien is a Canadian writer whose work explores the trans-cultural world of Asian art, politics, and family life within Canada’s diasporic Asian Communities. She was born in 1974 to a Malaysian Chinese father and a Hong Kong Chinese mother. Thien studied contemporary dance but switched to creative writing as an undergraduate in college. She earned her MFA in writing from the University of British Columbia.",
        color = Color.White,
        modifier = modifier.padding(horizontal = 16.dp)
    )

}
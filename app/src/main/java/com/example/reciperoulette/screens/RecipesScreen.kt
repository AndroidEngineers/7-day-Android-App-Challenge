package com.example.reciperoulette.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reciperoulette.R

enum class FilterCards {
    ALL, VEG, NON_VEG
}

@Preview
@Composable
fun RecipesScreen(modifier: Modifier = Modifier) {
    var selectedcard by remember {
        mutableStateOf(FilterCards.ALL)
    }

    fun selectCard(select: FilterCards) {
        selectedcard = select
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF0D0D0D))
    ) {
        Column {
            ToolBar()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FillterScreen(filterCards = FilterCards.ALL, selectedcard = selectedcard) {
                    selectCard(it)
                }
                FillterScreen(filterCards = FilterCards.VEG, selectedcard = selectedcard) {
                    selectCard(it)
                }
                FillterScreen(filterCards = FilterCards.NON_VEG, selectedcard = selectedcard) {
                    selectCard(it)
                }
            }

            LazyColumn(contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(10){
                    RecipeListItem()
                }
            }
        }
    }
}

@Preview
@Composable
private fun FillterScreen(
    modifier: Modifier = Modifier,
    filterCards: FilterCards = FilterCards.ALL,
    selectedcard: FilterCards = FilterCards.ALL,
    onClick: (filterCards: FilterCards) -> Unit = {}
) {
    val name = filterCards.name
    Box(
        modifier = modifier
            .background(
                color = if (filterCards == selectedcard) Color(0xFFFEFEFE) else Color.Black,
                shape = RoundedCornerShape(size = 23.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFF333333),
                shape = RoundedCornerShape(size = 23.dp)
            )
            .clickable {
                onClick(filterCards)
            }

    ) {
        Row(
            Modifier.padding(vertical = 8.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontWeight = FontWeight(500),
                    color = if (filterCards == selectedcard) Color(0xFF333333) else Color(0xFFFEFEFE)
                )
            )
        }
    }
}


@Preview
@Composable
private fun ToolBar(modifier: Modifier = Modifier) {
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
fun RecipeListItem(modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.recipe),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Recipe Name",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFEFEFE)
                    )
                )
Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Recipe Time: 48 min",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFEFEFE)
                    )
                )
            }

        }

    }

}
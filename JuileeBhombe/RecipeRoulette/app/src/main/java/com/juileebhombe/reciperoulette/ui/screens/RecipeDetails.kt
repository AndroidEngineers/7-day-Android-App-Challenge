package com.juileebhombe.reciperoulette.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juileebhombe.reciperoulette.R
import com.juileebhombe.reciperoulette.ui.theme.RecipeRouletteTheme

@Composable
fun RecipeDetails() {
    Column(Modifier.verticalScroll(rememberScrollState())) {

        TopHeader()

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Background Image", contentScale = ContentScale.FillBounds
        )
        Column(Modifier.padding(horizontal = 10.dp)) {
            RecipeSummary(data = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,")

            repeat(4)
            {
                Row {
                    RecipeDetail(data = "Time Required")
                    RecipeValue(data = "28 mins")
                }
            }

            RecipeHeaders(data = "Ingredients")
            Row(
                Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                repeat(10) {
                    Ingredient()
                }
            }


            RecipeHeaders(data = "Instructions")
            Text(
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                lineHeight = 18.sp,
                letterSpacing = (1).sp,
                fontFamily = FontFamily.SansSerif,
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
            )
        }

    }
}

@Composable
fun RecipeHeaders(data: String) {
    Text(
        text = data.uppercase(),
        modifier = Modifier.padding(vertical = 5.dp),
        fontWeight = FontWeight(500),
        fontSize = 18.sp,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.SansSerif
    )

}

@Composable
fun RecipeSummary(data: String) {
    Text(
        text = data,
        modifier = Modifier.padding(vertical = 5.dp),
        fontWeight = FontWeight(500),
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif
    )

}

@Composable
fun RecipeDetail(data: String) {
    Text(
        text = "$data  ".uppercase(),
        modifier = Modifier.padding(vertical = 2.dp),
        fontWeight = FontWeight(700),
        fontSize = 14.sp,
        letterSpacing = (-0.2).sp,
        fontFamily = FontFamily.SansSerif
    )

}

@Composable
fun RecipeValue(data: String) {
    Text(
        text = data,
        modifier = Modifier.padding(vertical = 2.dp),
        fontWeight = FontWeight(500),
        fontSize = 14.sp,
        letterSpacing = (-0.2).sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
fun Ingredient() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(100.dp)) {
        Image(
            modifier = Modifier
                .size(100.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "Chocolate", fontWeight = FontWeight(500),
            fontSize = 14.sp, lineHeight = (14).sp,
            fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center
        )
        Text(
            text = "11.0 g", fontWeight = FontWeight(500),
            fontSize = 14.sp, lineHeight = (14).sp,
            fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TopHeader() {
    Row {
        Text(
            modifier = Modifier
                .weight(9f)
                .padding(4.dp),
            fontWeight = FontWeight(900),
            fontSize = 20.sp,
            letterSpacing = (1).sp,
            fontFamily = FontFamily.SansSerif,
            text = "Recipe Name Lorem Ipsum is simply dummy text of the printing".uppercase()
        )
        IconButton(modifier = Modifier.weight(1.5f),
            onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Close,
                modifier = Modifier.size(45.dp),
                contentDescription = "Back"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeDetailsPreview() {
    RecipeRouletteTheme {
        RecipeDetails()
    }
}
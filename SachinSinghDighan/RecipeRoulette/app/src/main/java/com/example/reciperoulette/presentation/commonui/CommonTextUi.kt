package com.example.make_food.ui.commonui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

@Composable
fun ScreenTitleText(text: String, modifier: Modifier = Modifier){
    Text(text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Black,
        modifier = modifier
    )

}

@Composable
fun ScreenSubTitleText(text: String, modifier: Modifier = Modifier){
    Text(text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black,
        modifier = modifier
    )

}

@Composable
fun ScreenContentText(text: String, modifier: Modifier = Modifier){
    Text(text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Black,
        modifier = modifier
    )

}

@Composable
fun HorizontalListTitleText(text: String, modifier: Modifier = Modifier){
    Text(text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )

}


@Preview(showBackground = true)
@Composable
fun HorizontalListTitleTextPreview() {
    RecipeRouletteTheme {
        HorizontalListTitleText("RecipeName")
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    RecipeRouletteTheme {
        ScreenContentText("RecipeName")
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenSubTitlePreview() {
    RecipeRouletteTheme {
        ScreenSubTitleText("RecipeName")
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTextPreview() {
    RecipeRouletteTheme {
        ScreenTitleText("RecipeName")
    }
}

package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.Black

@Composable
fun MealText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = Black,
        textAlign = textAlign,
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun MealTextPreview() {
    MealText("Welcome to MealMate", modifier = Modifier, 36.sp, TextAlign.Center)
}
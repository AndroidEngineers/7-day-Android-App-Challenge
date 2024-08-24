// CustomTextComponents.kt
package com.example.taskninja20.reusable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.taskninja20.ui.theme.RobotoFontFamily

@Composable
fun CustomTextRegular(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = RobotoFontFamily
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        modifier = modifier
    )
}

@Composable
fun CustomTextBold(
    text: String,
    color: Color = Color.Black,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = RobotoFontFamily
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        modifier = modifier
    )
}

@Composable
fun CustomTextItalic(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = RobotoFontFamily
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal, // Italic is handled by font style
        fontFamily = fontFamily,
        modifier = modifier
    )
}

@Composable
fun CustomTextSemiBold(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = RobotoFontFamily
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        modifier = modifier
    )
}

@Composable
fun CustomTextMedium(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = RobotoFontFamily
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = FontWeight.Medium,
        fontFamily = fontFamily,
        modifier = modifier
    )
}

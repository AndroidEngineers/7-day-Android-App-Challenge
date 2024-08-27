package com.example.anifetch.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//  dark color scheme using Material3's darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.White,
    secondary = SecondaryColor,
    onSecondary = Color.White,
    background = BackgroundLight,
    surface = BackgroundDark,
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    error = ErrorColor,
    onError = Color.White
)

@Composable
fun AnifecthTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}


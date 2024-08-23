package com.example.taskninja20.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext



private val LightColorPalette = lightColorScheme(
    primary = YellowButtonColor,
    onPrimary = Color.White, // Text on buttons (for example)
    surface = Color.White, // Background color
    onSurface = BlackPrimaryTextColor, // Text color
    secondary = DarkGraySecondaryTextColor // Secondary text color
)

private val DarkColorPalette = darkColorScheme(
    primary = YellowButtonColor,
    onPrimary = Color.Black, // Text on buttons (for example)
    surface = Color.Black, // Background color
    onSurface = WhitePrimaryTextColor, // Text color
    secondary = LightGraySecondaryTextColor // Secondary text color
)



@Composable
fun TaskNinja20Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
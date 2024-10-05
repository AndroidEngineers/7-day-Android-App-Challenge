package com.mani.quotify007.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mani.quotify007.presentation.ui.theme.QuotifyAppTheme
import com.mani.quotify007.presentation.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotifyAppTheme {
                MainScreen()
            }
        }
    }
}
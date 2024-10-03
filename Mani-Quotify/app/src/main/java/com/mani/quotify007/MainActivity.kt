package com.mani.quotify007

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mani.quotify007.ui.theme.QuotifyAppTheme
import com.mani.quotify007.view.MainScreen

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
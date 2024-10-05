package com.mani.quotify007.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mani.quotify007.ui.navigation.MainScreen
import com.mani.quotify007.ui.screens.utils.onCopyText
import com.mani.quotify007.ui.screens.utils.shareQuote
import com.mani.quotify007.ui.theme.QuotifyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotifyAppTheme {
                MainScreen(
                    onCopyText = { quote -> onCopyText(this, quote) },
                    onShareClick = { quote -> shareQuote(this, quote) }
                )
            }
        }
    }
}
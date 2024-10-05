package com.mani.quotify007.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mani.quotify007.ui.navigation.MainScreen
import com.mani.quotify007.ui.screens.utils.ClipboardUtil
import com.mani.quotify007.ui.screens.utils.ShareUtil
import com.mani.quotify007.ui.theme.QuotifyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotifyAppTheme {
                MainScreen(
                    onCopyText = { quote -> ClipboardUtil.onCopyText(this, quote) },
                    onShareClick = { quote -> ShareUtil.shareQuote(this, quote) }
                )
            }
        }
    }
}
package com.mani.quotify007.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.mani.quotify007.ui.navigation.MainScreen
import com.mani.quotify007.ui.navigation.viewmodel.MainViewModel
import com.mani.quotify007.ui.screens.utils.onCopyText
import com.mani.quotify007.ui.screens.utils.shareQuote
import com.mani.quotify007.ui.theme.QuotifyAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotifyAppTheme {
                /* Collect the current state from the ViewModel as a State object.
                * the state is preserved across configuration changes and the UI is updated reactively */
                val state = viewModel.state.collectAsState().value
                MainScreen(
                    state,
                    onEvent = { event -> viewModel.onEvent(event) }
                )
            }
        }
        viewModel.copyTextEvent.observe(this) { quote ->
            onCopyText(this, quote)
        }
        viewModel.shareClickEvent.observe(this) { quote ->
            shareQuote(this, quote)
        }
    }
}
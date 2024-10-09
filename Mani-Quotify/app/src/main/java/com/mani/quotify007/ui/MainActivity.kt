package com.mani.quotify007.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.ui.navigation.MainScreen
import com.mani.quotify007.ui.navigation.viewmodel.MainViewModel
import com.mani.quotify007.ui.navigation.viewmodel.QuoteViewModelFactory
import com.mani.quotify007.ui.screens.utils.onCopyText
import com.mani.quotify007.ui.screens.utils.shareQuote
import com.mani.quotify007.ui.screens.utils.showToast
import com.mani.quotify007.ui.theme.QuotifyAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            factory = QuoteViewModelFactory(QuotifyApp.instance.quoteUseCase)
        ).get(MainViewModel::class.java)
    }
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
        viewModel.showToast.observe(this) { message ->
            message?.let { showToast(it) }
        }
    }
}
package com.mani.quotify007.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.ui.navigation.MainScreen
import com.mani.quotify007.ui.navigation.model.UiActionEvent
import com.mani.quotify007.ui.navigation.viewmodel.MainViewModel
import com.mani.quotify007.ui.navigation.viewmodel.QuoteViewModelFactory
import com.mani.quotify007.ui.screens.utils.onCopyText
import com.mani.quotify007.ui.screens.utils.shareQuote
import com.mani.quotify007.ui.screens.utils.showToast
import com.mani.quotify007.ui.theme.QuotifyAppTheme
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiActionEvent.collect { event ->
                    when (event) {
                        is UiActionEvent.CopyText -> onCopyText(this@MainActivity, event.quote)
                        is UiActionEvent.ShareClick -> shareQuote(this@MainActivity, event.quote)
                        is UiActionEvent.ShowToast -> showToast(event.message)
                        else -> { /* Do nothing */}
                    }
                }
            }
        }
    }
}
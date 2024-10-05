package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.data.QuoteRepositoryImplementation
import com.example.quotesapp.domain.usecases.GetQuote
import com.example.quotesapp.domain.usecases.LikedQuote
import com.example.quotesapp.domain.usecases.QuoteUseCase
import com.example.quotesapp.ui.home_screen.HomeScreen
import com.example.quotesapp.ui.theme.QuotesAppTheme
import com.example.quotesapp.ui.viewmodel.QuoteViewModel
import com.example.quotesapp.ui.viewmodel.QuoteViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var quoteViewModel:QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesAppTheme {

                Scaffold { paddingValues ->

//                    ----
//                    providing dependency
                    val quoteUseCase = QuoteUseCase(
                        getQuote = GetQuote(quoteRepository = QuoteRepositoryImplementation() ),
                        likedQuote = LikedQuote(quoteRepository = QuoteRepositoryImplementation() )
                    )
//                    ----

                    quoteViewModel = ViewModelProvider(this, QuoteViewModelFactory(quoteUseCase))
                        .get(QuoteViewModel::class.java)

                    HomeScreen(paddingValues,quoteViewModel)
                }
            }
        }
    }
}


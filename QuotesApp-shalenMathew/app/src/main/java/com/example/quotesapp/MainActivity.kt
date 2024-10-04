package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.example.quotesapp.ui.screen.HomeScreen
import com.example.quotesapp.ui.theme.QuotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesAppTheme {

                Scaffold { paddingValues ->
                    HomeScreen(paddingValues)
                }
            }
        }
    }
}


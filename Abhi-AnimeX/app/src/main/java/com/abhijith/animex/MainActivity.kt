package com.abhijith.animex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhijith.animex.ui.components.AnimeCard
import com.abhijith.animex.ui.theme.AnimeXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeXTheme {
                Scaffold() {
                    Box(modifier = Modifier.padding(it)) {
                        AnimeList()
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeList() {
    val names: List<String> = List(15) { "$it" }

    LazyColumn {
        items(names.count()) {
            AnimeCard(name = names[it])
        }
    }
}
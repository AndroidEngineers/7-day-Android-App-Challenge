package com.example.reciperoulette.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.make_food.ui.commonui.AppBar
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.navigation.Navigation
import com.example.reciperoulette.presentation.ui.theme.RecipeRouletteTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}


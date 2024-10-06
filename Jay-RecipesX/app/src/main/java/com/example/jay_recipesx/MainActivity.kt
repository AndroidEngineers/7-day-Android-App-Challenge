package com.example.jay_recipesx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jay_recipesx.features.RecipeHome.Presentation.Screens.RecipeHomeScreen
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeViewModel
import com.example.jay_recipesx.ui.theme.JayRecipesXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JayRecipesXTheme {
                val viewModel = hiltViewModel<RecipeViewModel>()
                val state =  viewModel.recipes.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeHomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state
                    )
                }
            }
        }
    }
}
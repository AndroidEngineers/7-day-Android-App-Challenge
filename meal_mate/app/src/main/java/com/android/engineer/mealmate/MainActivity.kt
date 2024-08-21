package com.android.engineer.mealmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.ui.theme.MealMateTheme
import com.android.engineer.mealmate.view.utils.constants.graph.RootNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealMateTheme {
               RootNavGraph(navHostController = rememberNavController())
            }
        }
    }
}

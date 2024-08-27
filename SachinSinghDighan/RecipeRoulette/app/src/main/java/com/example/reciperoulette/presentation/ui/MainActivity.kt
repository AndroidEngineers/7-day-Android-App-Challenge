package com.example.reciperoulette.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reciperoulette.di.component.DaggerActivityComponent
import com.example.reciperoulette.di.module.ActivityModule
import com.example.reciperoulette.presentation.RecipeRouletteApplication
import com.example.reciperoulette.presentation.ui.navigation.Navigation
import com.example.reciperoulette.presentation.viewModel.recipedetail.RecipeDetailViewModel
import com.example.reciperoulette.presentation.viewModel.recipelist.RecipeListViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var recipeListViewModel: RecipeListViewModel

    @Inject
    lateinit var recipeDetailViewModel: RecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)

        setContent {
            Navigation(recipeListViewModel, recipeDetailViewModel)
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .recipeRouletteApplicationComponent((application as RecipeRouletteApplication).application)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}


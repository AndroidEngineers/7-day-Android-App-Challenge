package com.example.reciperoulette.presentation

import android.app.Application
import com.example.reciperoulette.di.component.DaggerRecipeRouletteApplicationComponent
import com.example.reciperoulette.di.component.RecipeRouletteApplicationComponent
import com.example.reciperoulette.di.module.RecipeRouletteApplicationModule

class RecipeRouletteApplication: Application() {

    lateinit var application: RecipeRouletteApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        application = DaggerRecipeRouletteApplicationComponent
            .builder()
            .recipeRouletteApplicationModule(RecipeRouletteApplicationModule(this))
            .build()
        application.inject(this)

    }
}
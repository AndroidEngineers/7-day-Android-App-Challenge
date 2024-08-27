package com.example.reciperoulette.di.component

import android.content.Context
import com.example.reciperoulette.data.network.NetworkService
import com.example.reciperoulette.di.ApplicationContext
import com.example.reciperoulette.di.module.RecipeRouletteApplicationModule
import com.example.reciperoulette.domain.usecase.RecipeDetailUseCase
import com.example.reciperoulette.domain.usecase.RecipeListFromApiUseCase
import com.example.reciperoulette.presentation.RecipeRouletteApplication
import com.example.reciperoulette.presentation.util.DispatcherProvider
import com.example.reciperoulette.presentation.util.NetworkHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RecipeRouletteApplicationModule::class])
interface RecipeRouletteApplicationComponent {

    fun inject(application: RecipeRouletteApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

    fun getDispatcherProvider(): DispatcherProvider

    fun  getRecipeListFromApiUseCase(): RecipeListFromApiUseCase

    fun  getRecipeDetailUseCase(): RecipeDetailUseCase


}
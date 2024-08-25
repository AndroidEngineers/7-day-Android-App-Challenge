package com.example.reciperoulette.di.module

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.reciperoulette.di.ActivityContext
import com.example.reciperoulette.domain.usecase.RecipeDetailUseCase
import com.example.reciperoulette.domain.usecase.RecipeListFromApiUseCase
import com.example.reciperoulette.presentation.ui.base.ViewModelProviderFactory
import com.example.reciperoulette.presentation.util.DispatcherProvider
import com.example.reciperoulette.presentation.util.NetworkHelper
import com.example.reciperoulette.presentation.viewModel.recipedetail.RecipeDetailViewModel
import com.example.reciperoulette.presentation.viewModel.recipelist.RecipeListViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: ComponentActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity


    @Provides
    fun provideRecipeListViewModel(
        getRecipeListFromApiUseCase: RecipeListFromApiUseCase,
        networkHelper: NetworkHelper,
        dispatcherProvider: DispatcherProvider,
    ): RecipeListViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(RecipeListViewModel::class) {
                RecipeListViewModel(
                    getRecipeListFromApiUseCase,
                    networkHelper,
                    dispatcherProvider
                )
            })[RecipeListViewModel::class.java]
    }

    @Provides
    fun provideRecipeDetailViewModel(
        getRecipeDetailUseCase: RecipeDetailUseCase,
        networkHelper: NetworkHelper,
        dispatcherProvider: DispatcherProvider,
    ): RecipeDetailViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(RecipeDetailViewModel::class) {
                RecipeDetailViewModel(
                    getRecipeDetailUseCase,
                    networkHelper,
                    dispatcherProvider
                )
            })[RecipeDetailViewModel::class.java]
    }


}
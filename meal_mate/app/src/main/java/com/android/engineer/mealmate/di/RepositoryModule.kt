package com.android.engineer.mealmate.di

import android.app.Application
import com.android.engineer.mealmate.data.remote.MealAPI
import com.android.engineer.mealmate.data.repository.RecipeSearchRepositoryImpl
import com.android.engineer.mealmate.domain.repository.RecipeSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// creation of hilt module for repository
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeSearchRepository(api: MealAPI, appContext: Application): RecipeSearchRepository {
        return RecipeSearchRepositoryImpl(api, appContext)
    }

}

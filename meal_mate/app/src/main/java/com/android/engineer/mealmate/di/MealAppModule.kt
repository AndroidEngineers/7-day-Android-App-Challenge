package com.android.engineer.mealmate.di

import android.app.Application
import com.android.engineer.mealmate.data.local.datastore.MealDataStore
import com.android.engineer.mealmate.data.local.datastore.MealDataStoreImpl
import com.android.engineer.mealmate.data.remote.MealAPI
import com.android.engineer.mealmate.data.repository.AuthRepositoryImpl
import com.android.engineer.mealmate.data.repository.RecipeSearchRepositoryImpl
import com.android.engineer.mealmate.domain.repository.AuthRepository
import com.android.engineer.mealmate.domain.repository.RecipeSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealAppModule {

    @Provides
    @Singleton
    fun provideDataStoreRepo(appContext: Application): MealDataStore {
        return MealDataStoreImpl(appContext)
    }
    @Provides
    @Singleton
    fun provideRecipeSearchRepository(api: MealAPI): RecipeSearchRepository {
        return RecipeSearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: MealAPI): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}
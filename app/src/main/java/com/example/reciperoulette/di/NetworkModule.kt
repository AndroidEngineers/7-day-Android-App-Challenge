package com.example.reciperoulette.di

import com.example.reciperoulette.data.Network.api.RecipeApi
import com.example.reciperoulette.data.repositoryimpl.RecipesRepositoryImpl
import com.example.reciperoulette.domain.repository.RecipesRepository
import com.example.reciperoulette.screens.recipescreen.RecipesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi {
        return retrofit.create(RecipeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipe(recipeApi: RecipeApi): RecipesRepository {
        return RecipesRepositoryImpl(recipeApi)
    }
}
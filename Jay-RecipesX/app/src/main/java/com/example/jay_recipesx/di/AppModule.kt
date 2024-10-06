package com.example.jay_recipesx.di

import com.example.jay_recipesx.features.RecipeHome.Data.Remote.RecipeApi
import com.example.jay_recipesx.features.RecipeHome.Data.Repositories.RecipeRepoImpl
import com.example.jay_recipesx.features.RecipeHome.Domain.Repositories.IRecipeRepo
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepo(recipeApi: RecipeApi): IRecipeRepo {
        return RecipeRepoImpl(recipeApi)
    }
}

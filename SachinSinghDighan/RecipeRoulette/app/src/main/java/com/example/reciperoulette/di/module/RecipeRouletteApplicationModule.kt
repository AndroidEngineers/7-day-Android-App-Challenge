package com.example.reciperoulette.di.module

import android.content.Context
import com.example.reciperoulette.data.network.NetworkService
import com.example.reciperoulette.di.ApplicationContext
import com.example.reciperoulette.di.BaseUrl
import com.example.reciperoulette.di.repositoryimpl.RecipeDetailRepositoryImpl
import com.example.reciperoulette.di.repositoryimpl.RecipeRepositoryImpl
import com.example.reciperoulette.domain.repository.RecipeDetailRepository
import com.example.reciperoulette.domain.repository.RecipeRepository
import com.example.reciperoulette.presentation.RecipeRouletteApplication
import com.example.reciperoulette.presentation.util.AppConstant
import com.example.reciperoulette.presentation.util.DefaultDispatcherProvider
import com.example.reciperoulette.presentation.util.DispatcherProvider
import com.example.reciperoulette.presentation.util.NetworkHelper
import com.example.reciperoulette.presentation.util.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RecipeRouletteApplicationModule(private val application: RecipeRouletteApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @BaseUrl
    @Provides
    fun provideBaseUrl() = AppConstant.BASE_URL

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): NetworkService {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).client(
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        ).build().create(NetworkService::class.java)
    }


    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()


    @Provides
    @Singleton
    fun provideRecipeListRepository(networkService: NetworkService): RecipeRepository{
        return RecipeRepositoryImpl(networkService)
    }

    @Provides
    @Singleton
    fun provideRecipeDetailRepository(networkService: NetworkService): RecipeDetailRepository{
        return RecipeDetailRepositoryImpl(networkService)
    }



}
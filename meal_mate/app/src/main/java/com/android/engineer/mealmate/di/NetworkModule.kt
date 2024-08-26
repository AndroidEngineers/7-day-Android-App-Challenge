package com.android.engineer.mealmate.di

import com.android.engineer.mealmate.data.remote.MealAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// creation of hilt module for Network
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val API_BASE_URL = "https://api.spoonacular.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpBuilder = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(okHttpBuilder.build())
    }

    @Singleton
    @Provides
    fun providesMealAPI(retrofitBuilder: Retrofit.Builder): MealAPI {
        return retrofitBuilder.build().create(MealAPI::class.java)
    }

}

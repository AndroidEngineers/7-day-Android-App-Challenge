package com.abhijith.animex.di

import com.abhijith.animex.data.network.AnimeXApiService
import com.abhijith.animex.data.repository.ISeasonalAnimeListRepositoryImpl
import com.abhijith.animex.domain.repository.ISeasonalAnimeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSeasonalAnimeListRepository(apiService: AnimeXApiService): ISeasonalAnimeListRepository {
        return ISeasonalAnimeListRepositoryImpl(apiService)
    }
}
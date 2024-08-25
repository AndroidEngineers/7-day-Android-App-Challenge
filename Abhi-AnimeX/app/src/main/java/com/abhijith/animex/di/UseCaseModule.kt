package com.abhijith.animex.di

import com.abhijith.animex.domain.repository.ISeasonalAnimeListRepository
import com.abhijith.animex.domain.usecases.GetSeasonalAnimeListByOffsetUseCase
import com.abhijith.animex.domain.usecases.GetSeasonalAnimeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetSeasonalAnimeListUseCase(repository: ISeasonalAnimeListRepository): GetSeasonalAnimeListUseCase {
        return GetSeasonalAnimeListUseCase(repository)
    }

    @Provides
    fun provideGetSeasonalAnimeListByOffsetUseCase(repository: ISeasonalAnimeListRepository): GetSeasonalAnimeListByOffsetUseCase {
        return GetSeasonalAnimeListByOffsetUseCase(repository)
    }
}
package com.example.quotesapp.di

import com.example.quotesapp.data.remote.QuoteApi
import com.example.quotesapp.data.repository.QuoteRepositoryImplementation
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.usecases.GetQuote
import com.example.quotesapp.domain.usecases.LikedQuote
import com.example.quotesapp.domain.usecases.QuoteUseCase
import com.example.quotesapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

  @Singleton
@Provides
fun providesQuoteUsecase(getQuote: GetQuote,likedQuote: LikedQuote):QuoteUseCase{
return QuoteUseCase(getQuote = getQuote, likedQuote =likedQuote )
}

@Singleton
@Provides
fun providesQuoteRepository(api:QuoteApi):QuoteRepository{
        return QuoteRepositoryImplementation(api)
}



    @Singleton
    @Provides
    fun providesQuotesApi():QuoteApi{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(QuoteApi::class.java)
    }

}
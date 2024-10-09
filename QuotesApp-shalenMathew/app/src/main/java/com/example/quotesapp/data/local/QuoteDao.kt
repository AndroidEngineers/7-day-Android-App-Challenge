package com.example.quotesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotesapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow


@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertQuoteList(quote: List<Quote>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedQuote(quote: Quote)

    @Delete
   suspend fun deleteQuote(quote: Quote)

    @Query(" SELECT * FROM Quote WHERE liked==1 ")
    fun getAllLikedQuotes(): Flow<List<Quote>>

    @Query(" SELECT * FROM Quote ")
   suspend fun getAllQuotes():List<Quote>

    @Query("DELETE FROM quote")
    suspend fun deleteAll()

}
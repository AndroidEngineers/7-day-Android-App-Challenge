package com.mani.quotify007.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mani.quotify007.ui.screens.utils.GET_ALL_FAVORITE_QUOTES
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {
    @Query(GET_ALL_FAVORITE_QUOTES)
    fun getAllFavoriteQuotes(): Flow<List<FavoriteQuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteQuote(quote: FavoriteQuoteEntity)

    @Delete
    suspend fun deleteFavoriteQuote(quote: FavoriteQuoteEntity)
}
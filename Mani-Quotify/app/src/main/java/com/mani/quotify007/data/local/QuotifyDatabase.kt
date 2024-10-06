package com.mani.quotify007.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteQuoteEntity::class], version = 1)
abstract class QuotifyDatabase: RoomDatabase() {
    abstract fun favoriteQuoteDao(): FavoriteQuoteDao
}
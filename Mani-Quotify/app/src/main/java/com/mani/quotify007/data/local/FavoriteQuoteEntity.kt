package com.mani.quotify007.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mani.quotify007.ui.screens.utils.QUOTE_TABLE_NAME

@Entity(tableName = QUOTE_TABLE_NAME)
data class FavoriteQuoteEntity(
    @PrimaryKey val id: Int = 0,
    val text: String,
    val author: String
)

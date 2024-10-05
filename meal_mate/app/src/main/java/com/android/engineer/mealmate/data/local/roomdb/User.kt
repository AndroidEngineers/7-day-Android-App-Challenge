package com.android.engineer.mealmate.data.local.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val hash: String
)


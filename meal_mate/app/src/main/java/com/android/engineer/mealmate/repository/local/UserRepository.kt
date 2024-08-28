package com.android.engineer.mealmate.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.engineer.mealmate.data.local.roomdb.User

interface UserRepository {

    suspend fun insertUser(user: User): Long

    suspend fun getUser(username: String, password: String): User?

}

package com.android.engineer.mealmate.data.local.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users_table WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?

    @Query("SELECT email FROM USERS_TABLE WHERE username = :username")
    suspend fun getEmail(username: String): String?

}

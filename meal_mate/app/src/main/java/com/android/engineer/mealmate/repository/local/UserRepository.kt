package com.android.engineer.mealmate.repository.local

import com.android.engineer.mealmate.data.local.roomdb.User

interface UserRepository {

    suspend fun insertUser(user: User): Long

    suspend fun getUser(username: String, password: String): User?

    suspend fun getEmail(username: String): String
}

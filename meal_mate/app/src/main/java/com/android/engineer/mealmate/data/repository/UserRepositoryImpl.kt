package com.android.engineer.mealmate.data.repository

import com.android.engineer.mealmate.data.local.roomdb.User
import com.android.engineer.mealmate.data.local.roomdb.UserDao
import com.android.engineer.mealmate.repository.local.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao): UserRepository {
    override suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }
    override suspend fun getUser(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }
}

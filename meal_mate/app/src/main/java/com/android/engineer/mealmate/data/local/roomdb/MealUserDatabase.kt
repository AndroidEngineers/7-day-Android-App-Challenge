package com.android.engineer.mealmate.data.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class MealUserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

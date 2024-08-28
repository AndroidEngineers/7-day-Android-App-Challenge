package com.android.engineer.mealmate.data.local.roomdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.android.engineer.mealmate.data.utils.USER_DATABASE_NAME
import com.android.engineer.mealmate.repository.local.UserRepository

@Database(entities = [User::class], version = 1)
abstract class MealUserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MealUserDatabase? = null
//        fun getMealUserDatabase(context: Context): MealUserDatabase {
//            if(INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        MealUserDatabase::class.java,
//                        USER_DATABASE_NAME
//                    ).build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
}

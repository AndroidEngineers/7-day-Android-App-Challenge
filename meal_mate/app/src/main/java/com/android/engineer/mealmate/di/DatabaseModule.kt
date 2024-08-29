package com.android.engineer.mealmate.di

import android.content.Context
import androidx.room.Room
import com.android.engineer.mealmate.data.local.roomdb.MealUserDatabase
import com.android.engineer.mealmate.data.local.roomdb.UserDao
import com.android.engineer.mealmate.data.utils.USER_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideUserDao(mealUserDatabase: MealUserDatabase): UserDao {
        return mealUserDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): MealUserDatabase {
        return Room.databaseBuilder(
            context = context,
            MealUserDatabase::class.java,
            USER_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
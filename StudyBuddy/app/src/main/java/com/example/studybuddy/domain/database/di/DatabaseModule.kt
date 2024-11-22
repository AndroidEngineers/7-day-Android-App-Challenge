package com.example.studybuddy.domain.database.di

import android.app.Application
import androidx.room.Room
import com.example.studybuddy.domain.database.local.AppDatabase
import com.example.studybuddy.domain.database.local.SessionDao
import com.example.studybuddy.domain.database.local.SubjectDao
import com.example.studybuddy.domain.database.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


@Provides
@Singleton //it will make sure that there is only one instance of the provided function

    fun provideDatabase(
        application: Application
    ):AppDatabase{
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "studybuddy.db"
        ).build()
    }



    @Provides
    @Singleton

    fun provideSubjectDao(database: AppDatabase) :SubjectDao{
        return database.subjectDao()

    }

    @Provides
    @Singleton

    fun provideTaskDao(database: AppDatabase) : TaskDao {
        return database.taskDao()

    }

    @Provides
    @Singleton

    fun provideSessionDao(database: AppDatabase) :SessionDao{
        return database.sessionDao()

    }




}
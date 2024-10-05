package com.example.studybuddy.domain.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Subject
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.utils.ColorListConverter


@Database(entities = [Subject::class, Session::class, Task::class], version = 1)

@TypeConverters(ColorListConverter::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun subjectDao(): SubjectDao
    abstract fun sessionDao(): SessionDao
    abstract fun taskDao(): TaskDao

}
package com.example.studybuddy.domain.database.di

import com.example.studybuddy.domain.database.repositoryImpl.SessionRepositoryImpl
import com.example.studybuddy.domain.database.repositoryImpl.SubjectRepositoryImpl
import com.example.studybuddy.domain.database.repositoryImpl.TaskRepositoryImpl
import com.example.studybuddy.domain.model.repository.SessionRepository
import com.example.studybuddy.domain.model.repository.SubjectRepository
import com.example.studybuddy.domain.model.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Singleton
    @Binds
    abstract fun bindSubjectRepository(
        impl:SubjectRepositoryImpl
    ):SubjectRepository

    @Singleton
    @Binds
    abstract fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository



    @Singleton
    @Binds
    abstract fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository



}
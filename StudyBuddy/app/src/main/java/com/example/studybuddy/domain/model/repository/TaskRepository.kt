package com.example.studybuddy.domain.model.repository

import com.example.studybuddy.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun upsertTask (task: Task)


    suspend fun deleteTaskById(taskId: Int)


    suspend fun deleteTaskBySubjectId(subjectId: Int)


    suspend fun getTaskByTaskId(taskId: Int): Task?


    fun getTaskBySubjectId(subjectId: Int): Flow<List<Task>>

    fun getCompletedTaskBySubjectId(subjectId: Int): Flow<List<Task>>

    fun getAllTasks(): Flow<List<Task>>

    fun getAllUpcomingTasks(): Flow<List<Task>>
}
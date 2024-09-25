package com.example.studybuddy.domain.database.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.studybuddy.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
   suspend fun upsertTask (task: Task)


   @Query("DELETE FROM task WHERE taskId = :taskId")
   suspend fun deleteTaskById(taskId: Int)


   @Query("DELETE FROM task WHERE taskSubjectId = :subjectId")
   suspend fun deleteTaskBySubjectId(subjectId: Int)


   @Query("SELECT * FROM task WHERE taskId = :taskId")
   suspend fun getTaskByTaskId(taskId: Int): Task?


   @Query("SELECT * FROM task WHERE taskSubjectId = :subjectId")
   fun getTaskBySubjectId(subjectId: Int): Flow<List<Task>>

   @Query("SELECT * FROM task")
   fun getAllTasks(): Flow<List<Task>>
}
package com.example.studybuddy.domain.database.repositoryImpl

import com.example.studybuddy.domain.database.local.TaskDao
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.domain.model.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
):TaskRepository {
    override suspend fun upsertTask(task: Task) {
        return taskDao.upsertTask(task)
    }

    override suspend fun deleteTaskById(taskId: Int) {
        return taskDao.deleteTaskById(taskId)
    }

    override suspend fun deleteTaskBySubjectId(subjectId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskByTaskId(taskId: Int): Task? {
        return taskDao.getTaskByTaskId(taskId)
    }

    override fun getTaskBySubjectId(subjectId: Int): Flow<List<Task>> {
        return taskDao.getTaskBySubjectId(subjectId)
            .map { tasks -> tasks.filter { it.isCompleted.not() } }
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun getCompletedTaskBySubjectId(subjectId: Int): Flow<List<Task>> {
        return taskDao.getTaskBySubjectId(subjectId)
            .map { tasks -> tasks.filter { it.isCompleted} }
    }

    override fun getAllUpcomingTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
            .map { tasks ->tasks.filter { it.isCompleted.not() } }
            .map { tasks -> sortTasks(tasks) }
    }

private fun sortTasks(tasks: List<Task>): List<Task> {
    return tasks.sortedWith(compareBy<Task> {it.dueDate  }. thenByDescending { it.priority })
}

}
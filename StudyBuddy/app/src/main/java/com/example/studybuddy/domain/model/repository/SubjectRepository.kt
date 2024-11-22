package com.example.studybuddy.domain.model.repository

import com.example.studybuddy.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    suspend fun upsertSubject(subject: Subject)

    fun getTotalSubjectCount(): Flow<Int>

    fun getTotalGoalHours(): Flow<Float>

    suspend fun getSubjectById(subjectId : Int) : Subject?

    suspend fun deleteSubjectById(subjectId : Int)

    fun getAllSubjects() : Flow<List<Subject>>

}
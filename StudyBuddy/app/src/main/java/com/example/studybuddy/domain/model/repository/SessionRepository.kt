package com.example.studybuddy.domain.model.repository

import com.example.studybuddy.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun insertSession(session: Session)

    suspend fun deleteSession(session: Session)

    fun getAllSessions(): Flow<List<Session>>


    fun getRecentFiveSessions(): Flow<List<Session>>

    suspend fun getSessionBySubjectId(subjectId: Int): Flow<List<Session>>

    fun getTotalSessionDuration(): Flow<Long>

    fun getTotalSessionDurationBySubjectId(subjectId: Int): Flow<Long>

    suspend fun deleteSessionBySubjectId(subjectId: Int)


}
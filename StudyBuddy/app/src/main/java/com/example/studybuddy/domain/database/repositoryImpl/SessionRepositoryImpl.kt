package com.example.studybuddy.domain.database.repositoryImpl

import com.example.studybuddy.domain.database.local.SessionDao
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao
):SessionRepository {
    override suspend fun insertSession(session: Session) {
        sessionDao.insertSession(session)
    }

    override suspend fun deleteSession(session: Session) {
        return sessionDao.deleteSession(session)
    }

    override fun getAllSessions(): Flow<List<Session>> {
       return sessionDao.getAllSessions().map {
           sessions-> sessions.sortedByDescending { it.date }
       }
    }


    override fun getRecentFiveSessions(): Flow<List<Session>> {
        return sessionDao.getAllSessions().map {
                sessions-> sessions.sortedByDescending { it.date }
        }
            .take(5)
    }


    override suspend fun getSessionBySubjectId(subjectId: Int): Flow<List<Session>> {
        TODO("Not yet implemented")
    }

    override fun getTotalSessionDuration(): Flow<Long> {
        return sessionDao.getTotalSessionDuration()
    }

    override fun getTotalSessionDurationBySubjectId(subjectId: Int): Flow<Long> {
        return sessionDao.getTotalDurationBySubjectId(subjectId)
    }

    override suspend fun deleteSessionBySubjectId(subjectId: Int) {
        TODO("Not yet implemented")
    }
}
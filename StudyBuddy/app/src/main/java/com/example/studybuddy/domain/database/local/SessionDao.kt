package com.example.studybuddy.domain.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.studybuddy.domain.model.Session
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {


    @Insert
    suspend fun insertSession(session: Session)

    @Delete
    suspend fun deleteSession(session: Session)


    @Query("SELECT * FROM session")
    fun getAllSessions(): Flow<List<Session>>


    @Query("SELECT * FROM session WHERE sessionSubjectId = :subjectId")
    fun getSessionBySubjectId(subjectId: Int): Flow<List<Session>>

    @Query("SELECT SUM(duration) FROM Session")
    fun getTotalSessionDuration(): Flow<Long>


    @Query("SELECT SUM (duration) FROM session WHERE sessionSubjectId = :subjectId")
    fun getTotalDurationBySubjectId(subjectId: Int): Flow<Long>

    @Query("SELECT SUM (duration) FROM Session ")
    fun getTotalDurationBySubjectId(): Flow<Long>


    @Query("DELETE  FROM session WHERE sessionSubjectId = :subjectId")
    suspend fun deleteSessionBySubjectId(subjectId: Int)
}
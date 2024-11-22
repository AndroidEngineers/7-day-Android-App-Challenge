package com.example.studybuddy.domain.database.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.studybuddy.domain.model.Subject
import kotlinx.coroutines.flow.Flow


@Dao
interface SubjectDao {

    @Upsert
   suspend fun upsertSubject(subject: Subject)


    @Query("SELECT COUNT(*) FROM SUBJECT")
    fun getTotalSubjectCount(): Flow<Int>


    @Query("SELECT SUM(goalHour) FROM SUBJECT")
    fun getTotalGoalHours():Flow<Float>


    @Query("SELECT * FROM SUBJECT WHERE subjectId = :subjectId")
    suspend fun getSubjectById(subjectId : Int) : Subject?


    @Query("DELETE FROM SUBJECT WHERE subjectId = :subjectId")
    suspend fun deleteSubjectById(subjectId : Int)


    @Query("SELECT * FROM SUBJECT")
    fun getAllSubjects() : Flow<List<Subject>>



}
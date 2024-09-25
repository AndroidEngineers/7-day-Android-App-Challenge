package com.example.studybuddy.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(
    val sessionSubjectId:Int,
    @PrimaryKey(autoGenerate = true)
    val sessionId:Int? = null,
    val date:Long,
    val duration:Long,
    val relatedToSubject:String) {


}
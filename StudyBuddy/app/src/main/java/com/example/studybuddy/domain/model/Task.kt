package com.example.studybuddy.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    val title: String,
    val description: String,
    val dueDate:Long,
    val dueTime:Long,
    val setReminder:Boolean,
    val priority:Int,
    val relatedToSubject:String,
    val isCompleted:Boolean,
    @PrimaryKey(autoGenerate = true)
    val TaskId: Int? = null,
    val taskSubjectId:Int
)

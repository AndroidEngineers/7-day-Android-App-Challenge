package com.example.studybuddy.view.subject

import androidx.compose.ui.graphics.Color
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Subject
import com.example.studybuddy.domain.model.Task

data class subjectState(
    val currentSubjectId: Int? = null,
    val subjectName: String = "",
    val goalStudyHours: String = "",
    val studiedHours: Float = 0f,
    val subjectCardColor:List<Color> = Subject.subjectCardColor.random(),
    val upcomingTasks:List<Task> = emptyList(),
    val completedTasks:List<Task> = emptyList(),
    val recentSessions:List<Session> = emptyList(),
    val session:Session? = null,
    val progress: Float = 0f,
    val isLoading : Boolean = false
)

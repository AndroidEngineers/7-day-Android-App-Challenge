package com.example.studybuddy.view.dashboard

import androidx.compose.ui.graphics.Color
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Subject

data class dashboardVariable(
    val totalSubjectCount: Int = 0,
    val totalStudiedHours: Float = 0f,
    val totalGoalHours: Float= 0f,
    val subjects:List<Subject> = emptyList(),
    val subjectName: String = "",
    val goalStudyHours: String = "",
    val subjectCardColors: List<Color> = Subject.subjectCardColor.random(),
    val session: Session? = null
)

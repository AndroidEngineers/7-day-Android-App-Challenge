package com.example.studybuddy.view.subject

import androidx.compose.ui.graphics.Color
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.view.dashboard.DashboardEvent

sealed class SubjectEvent {
    data object UpdateSubject : SubjectEvent()

    data object DeleteSubject : SubjectEvent()

    data object DeleteSession : SubjectEvent()

    data object UpdateProgress: SubjectEvent()

    data class OnTaskIsCompleteChange(val task: Task): SubjectEvent()

    data class OnSubjectCardColorChange(val colors:List<Color>): SubjectEvent()

    data class OnSubjectNameChange(val name:String): SubjectEvent()

    data class OnGoalStudyHoursChange(val hours:String): SubjectEvent()

    data class OnDeleteSessionButtonClicked(val session: Session): SubjectEvent()
}

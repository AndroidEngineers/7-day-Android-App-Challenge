package com.example.studybuddy.view.dashboard

import androidx.compose.ui.graphics.Color
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Task

sealed class DashboardEvent {

    data object SaveSubject:DashboardEvent()

    data object DeleteSession:DashboardEvent()

    data class OnDeleteSessionButtonClicked(val session: Session):DashboardEvent()

    data class OnTaskIsCompleteChange(val task: Task):DashboardEvent()

    data class OnSubjectCardColorChange(val colors:List<Color>):DashboardEvent()

    data class OnSubjectNameChange(val name:String):DashboardEvent()

    data class OnGoalStudyHoursChange(val hours:String):DashboardEvent()
}
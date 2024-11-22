package com.example.studybuddy.view.task

import com.example.studybuddy.domain.model.Subject
import com.example.studybuddy.utils.Priority

sealed class TaskEvent {
    data class OnTitleChanged(val title: String) : TaskEvent()

    data class OnDescriptionChanged(val description: String) : TaskEvent()

    data class OnDateChanged(val millis: Long?) : TaskEvent()

    data class OnTimeChanged(val timeInMillis: Long?) : TaskEvent()

    data class onSetReminderchanged(val setReminder: Boolean):TaskEvent()

    data class OnPriorityChanged(val priority: Priority) : TaskEvent()

    data class OnRelatedSubjectSelect(val subject: Subject) : TaskEvent()

    data object OnIsCompletedChanged : TaskEvent()

    data object SaveTask : TaskEvent()

    data object DeleteTask : TaskEvent()
}
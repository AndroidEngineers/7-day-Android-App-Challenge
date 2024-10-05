package com.example.studybuddy.view.session

import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Subject

data class SessionState(
    val subjects: List<Subject> = emptyList(),
    val sessions: List<Session> = emptyList(),
    val relatedToSubject: String? = null,
    val subjectId: Int? = null,
    val session: Session? = null
)

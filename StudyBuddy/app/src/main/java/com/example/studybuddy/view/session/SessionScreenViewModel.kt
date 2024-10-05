package com.example.studybuddy.view.session

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.repository.SessionRepository
import com.example.studybuddy.domain.model.repository.SubjectRepository
import com.example.studybuddy.utils.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class SessionScreenViewModel @Inject constructor(
    subjectRepository: SubjectRepository,
    private val sessionRepository: SessionRepository

):ViewModel() {


    private val _state = MutableStateFlow(SessionState())
    val state = combine(
        _state,
        subjectRepository.getAllSubjects(),
        sessionRepository.getAllSessions()
    )
    {
        state, subjects, sessions ->
        state.copy(
            subjects = subjects,
            sessions = sessions
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SessionState()
    )
    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()


    fun onEvent(event: SessionEvent){
        when(event){
            SessionEvent.NotifyToUpdateSubject->  notifyToUpdateSubject()
            SessionEvent.DeleteSession -> deleteSession()
            is SessionEvent.OnDeleteSessionButtonClicked -> {
                _state.update {
                    it.copy(session = event.session)
                }
            }
            is SessionEvent.OnRelatedSubjectChange -> {
                _state.update {
                    it.copy(
                        relatedToSubject = event.subject.name,
                        subjectId = event.subject.SubjectId
                    )
                }
            }
            is SessionEvent.SaveSession -> insertSession(event.duration)
            is SessionEvent.UpdateSubjectIdAndRelatedSubject -> {
                _state.update {
                    it.copy(
                        relatedToSubject = event.relatedToSubject,
                        subjectId = event.subjectId
                    )
                }
            }
        }
    }

    private fun notifyToUpdateSubject() {
       viewModelScope.launch {
           if (state.value.subjectId == null || state.value.relatedToSubject == null)
           {
               _snackbarEventFlow.emit(
                   SnackbarEvent.ShowSnackbar(
                       "Please choose related subject to this session"))
           }
       }
    }

    private fun deleteSession() {
        viewModelScope.launch {
            try {
                state.value.session?.let {
                    sessionRepository.deleteSession(it)
                }
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Session deleted successfully"))
            }
            catch (e:Exception)
            {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Failed to delete Session ${e.message}", SnackbarDuration.Long))
            }
        }
    }

    private fun insertSession(duration: Long) {
        viewModelScope.launch {
            if (duration< 36){
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Single session must be more than 36 sec")
                )
                return@launch

            }
            try {
                withContext(Dispatchers.IO){
                    sessionRepository.insertSession(
                        session = Session(
                            sessionSubjectId = state.value.subjectId ?: -1,
                            relatedToSubject = state.value.relatedToSubject ?: "",
                            date = Instant.now().toEpochMilli(),
                            duration = duration
                        )
                    )
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar(
                            "Session Saved successfully"))
                    _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                }
            }
            catch (e:Exception){
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Failed to save Session ${e.message}", SnackbarDuration.Long))
            }
        }
    }

}
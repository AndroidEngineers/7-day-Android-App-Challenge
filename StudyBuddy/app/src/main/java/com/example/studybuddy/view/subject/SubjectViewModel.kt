package com.example.studybuddy.view.subject

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studybuddy.domain.model.Subject
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.domain.model.repository.SessionRepository
import com.example.studybuddy.domain.model.repository.SubjectRepository
import com.example.studybuddy.domain.model.repository.TaskRepository
import com.example.studybuddy.utils.SnackbarEvent
import com.example.studybuddy.utils.toHours
import com.example.studybuddy.view.dashboard.dashboardVariable
import com.example.studybuddy.view.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository,
    private val sessionRepository: SessionRepository,
    private val taskRepository: TaskRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {



    private val navArgs: SubjectScreenNavArgs = savedStateHandle.navArgs()

private val _state = MutableStateFlow(subjectState())
    val state = combine(
        _state,
        taskRepository.getTaskBySubjectId(navArgs.subjectId),
        taskRepository.getCompletedTaskBySubjectId(navArgs.subjectId),
        sessionRepository.getRecentFiveSessions(),
        sessionRepository.getTotalSessionDurationBySubjectId(navArgs.subjectId)
    )
    {
        state, upcomingTask, completedTask, recentSession, totalSessionDuration ->
        state.copy(
            upcomingTasks = upcomingTask,
            completedTasks = completedTask,
            recentSessions = recentSession,
            studiedHours = totalSessionDuration.toHours()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = subjectState()
    )

    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()


    init {
        fetchSubject()
    }
    fun onEvent(event: SubjectEvent)
    {
        when(event){
            SubjectEvent.DeleteSession -> deleteSession()
            SubjectEvent.DeleteSubject -> deleteSubject()
            is SubjectEvent.OnDeleteSessionButtonClicked -> {
                _state.update {
                    it.copy(session = event.session)
                }
            }
            is SubjectEvent.OnGoalStudyHoursChange -> {
                _state.update {
                    it.copy(
                        goalStudyHours = event.hours
                    )
                }
            }
            is SubjectEvent.OnSubjectCardColorChange -> {
                _state.update {
                    it.copy(
                        subjectCardColor = event.colors
                    )
                }
            }
            is SubjectEvent.OnSubjectNameChange -> {
                _state.update {
                    it.copy(
                        subjectName = event.name
                    )
                }
            }
            is SubjectEvent.OnTaskIsCompleteChange -> {updateTask(event.task)}
            SubjectEvent.UpdateSubject -> updateSubject()
            SubjectEvent.UpdateProgress -> {
                val goalStudyHours = state.value.goalStudyHours.toFloatOrNull()?: 1f
                _state.update {
                    it.copy(
                        progress = (state.value.studiedHours / goalStudyHours).coerceIn(0f,1f)
                    )
                }
            }
        }
    }

    
    private fun updateSubject() {
        viewModelScope.launch {
            try {
                subjectRepository.upsertSubject(
                    subject = Subject(
                        SubjectId = state.value.currentSubjectId,
                        name = state.value.subjectName,
                        goalHour = state.value.goalStudyHours.toFloatOrNull()?: 1f,
                        color = state.value.subjectCardColor.map { it.toArgb() }
                    )
                )
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Subject Updated Succesfully"
                    )
                )
            }
            catch (e: Exception)
            {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Failed to update subject. ${e.message}"
                    )
                )
            }
        }
    }


    private fun fetchSubject(){
        viewModelScope.launch {
            subjectRepository
                .getSubjectById(navArgs.subjectId)?.let { subject->
                    _state.update {
                        it.copy(
                            subjectName = subject.name,
                            goalStudyHours = subject.goalHour.toString(),
                            subjectCardColor = subject.color.map { Color(it) },
                            currentSubjectId = subject.SubjectId
                        )
                    }
                }
        }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                taskRepository.upsertTask(
                    task = task.copy(isCompleted = !task.isCompleted)
                )
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Task marked as upcoming task"
                    )
                )
            }
            catch (e:Exception){
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Couldn't update the task. ${e.message}",
                        SnackbarDuration.Long
                    )
                )
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun deleteSubject()
    {
        viewModelScope.launch {
            try {
                val currentSubjectId = state.value.currentSubjectId

                    if (currentSubjectId!=null)
                    {
                        withContext(Dispatchers.IO){
                            subjectRepository.deleteSubjectById(subjectId = currentSubjectId)
                            _snackbarEventFlow.emit(
                                SnackbarEvent.ShowSnackbar("Subject Deleted Successfully")
                            )
                            _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                        }

                    }

                else{
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar("Subject not found")
                    )
                    }

            }
            catch (e:Exception)
            {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar("Failed to delete subject. ${e.message}",
                        duration = SnackbarDuration.Long)

                )
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

}
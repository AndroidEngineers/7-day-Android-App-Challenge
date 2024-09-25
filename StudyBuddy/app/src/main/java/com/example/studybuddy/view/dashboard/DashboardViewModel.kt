package com.example.studybuddy.view.dashboard

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Subject
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.domain.model.repository.SessionRepository
import com.example.studybuddy.domain.model.repository.SubjectRepository
import com.example.studybuddy.domain.model.repository.TaskRepository
import com.example.studybuddy.utils.SnackbarEvent
import com.example.studybuddy.utils.toHours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository,
    private val sessionRepository: SessionRepository,
    private val taskRepository: TaskRepository
):ViewModel()
{
    private val _state = MutableStateFlow(dashboardVariable())

    val state = combine(
        _state,
        subjectRepository.getTotalSubjectCount(),
        subjectRepository.getTotalGoalHours(),
        subjectRepository.getAllSubjects(),
        sessionRepository.getTotalSessionDuration()
    ){
        _state, totalSubjectCount, totalGoalHours, subjects, totalSessionDuration ->
        _state.copy(
            totalSubjectCount = totalSubjectCount,
            totalGoalHours = totalGoalHours,
            subjects = subjects,
            totalStudiedHours = totalSessionDuration.toHours()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = dashboardVariable()
    )


    val tasks: StateFlow<List<Task>> = taskRepository.getAllUpcomingTasks()
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val session: StateFlow<List<Session>> = sessionRepository.getRecentFiveSessions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()


    fun onEvent(event: DashboardEvent){
        when(event){
            DashboardEvent.DeleteSession -> deleteSession()
            is DashboardEvent.OnDeleteSessionButtonClicked -> {
                _state.update {
                    it.copy(session = event.session)
                }
            }
            is DashboardEvent.OnGoalStudyHoursChange -> {

                _state.update {
                    it.copy(goalStudyHours = event.hours)
                }
            }
            is DashboardEvent.OnSubjectCardColorChange -> {

                _state.update {
                    it.copy(subjectCardColors = event.colors)
                }
            }
            is DashboardEvent.OnSubjectNameChange -> {

                _state.update {
                    it.copy(subjectName = event.name)
                }
            }
            is DashboardEvent.OnTaskIsCompleteChange -> {
                updateTask(event.task)
            }
            DashboardEvent.SaveSubject -> saveSubject()
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
                        "Task marked as completed"
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

    private fun saveSubject() {
       viewModelScope.launch {
          try {
              subjectRepository.upsertSubject(
                  subject = Subject(
                      name = state.value.subjectName,
                      goalHour = state.value.goalStudyHours.toFloatOrNull()?: 1f,
                      color = state.value.subjectCardColors.map { it.toArgb() },

                      )
              )
              _state.update {
                  it.copy(
                      subjectName = "",
                      goalStudyHours = "",
                      subjectCardColors = Subject.subjectCardColor.random()
                  )
              }
              _snackbarEventFlow.emit(
                  SnackbarEvent.ShowSnackbar(
                      "Subject Added Successfully"
                  )
              )
          }
          catch (e:Exception){
              _snackbarEventFlow.emit(
                  SnackbarEvent.ShowSnackbar(
                      "Failed to add subject. ${e.message}",
                      SnackbarDuration.Long
                  )
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
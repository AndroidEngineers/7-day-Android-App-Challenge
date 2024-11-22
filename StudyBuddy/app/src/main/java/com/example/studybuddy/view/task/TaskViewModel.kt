package com.example.studybuddy.view.task

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.domain.model.repository.SubjectRepository
import com.example.studybuddy.domain.model.repository.TaskRepository
import com.example.studybuddy.utils.Priority
import com.example.studybuddy.utils.SnackbarEvent
import com.example.studybuddy.view.navArgs
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
class TaskViewModel @Inject constructor(
private val taskRepository: TaskRepository,
    private val subjectRepository: SubjectRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navArgs:TaskScreenNavArgs = savedStateHandle.navArgs()
    private val _state = MutableStateFlow(TaskState())
    val state = combine(
        _state,
        subjectRepository.getAllSubjects(),
    ){
        state, subjects ->
        state.copy(subjects = subjects)
    }.stateIn(
        scope = viewModelScope,
        initialValue = TaskState(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackbarEventFlow = _snackbarEventFlow.asSharedFlow()

    init {
        fetchTaskById()
        fetchSubject()
    }


    fun onEvent(event: TaskEvent){
        when(event){
            TaskEvent.DeleteTask -> deleteTask()
            is TaskEvent.OnDateChanged -> {
                _state.update {
                    it.copy(dueDate = event.millis)
                }
            }
            is TaskEvent.OnDescriptionChanged -> {
                _state.update {
                    it.copy(description = event.description)
                }
            }
            TaskEvent.OnIsCompletedChanged -> {
                _state.update {
                    it.copy(isTaskComplete = !_state.value.isTaskComplete,
                        isReminderSet = !_state.value.isReminderSet)
                }
            }
            is TaskEvent.OnPriorityChanged -> {
                _state.update {
                    it.copy(priority = event.priority)
                }
            }
            is TaskEvent.OnRelatedSubjectSelect -> {
                _state.update {
                    it.copy(relatedToSubject = event.subject.name,
                        subjectId = event.subject.SubjectId)
                }
            }
            is TaskEvent.OnTitleChanged -> {
                _state.update {
                    it.copy(title = event.title)
                }
            }
            TaskEvent.SaveTask -> saveTask()
            is TaskEvent.OnTimeChanged -> {
                _state.update {
                    it.copy(dueTime = event.timeInMillis)
                }
            }

            is TaskEvent.onSetReminderchanged-> {
                _state.value = state.value.copy(isReminderSet = event.setReminder)
            }

            else -> {}
        }
    }

    private fun saveTask() {
        viewModelScope.launch {
            if(state.value.subjectId ==null|| state.value.relatedToSubject==null)
            {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Please fill all the fields before saving",SnackbarDuration.Long))


                return@launch

            }

            try {
                withContext(Dispatchers.IO){//to run it on io thread

                    taskRepository.upsertTask(
                        task = Task(
                            title = state.value.title,
                            description = state.value.description,
                            dueDate = state.value.dueDate ?: Instant.now().toEpochMilli(),
                            isCompleted = state.value.isTaskComplete,
                            priority = state.value.priority.value,
                            relatedToSubject = state.value.relatedToSubject!!,
                            taskSubjectId = state.value.subjectId!!,
                            TaskId = state.value.currentTaskId,
                            dueTime = state.value.dueTime?:Instant.now().toEpochMilli(),
                            setReminder = state.value.isReminderSet
                        )
                    )
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar(
                            "Task Saved successfully"))
                    _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                }
            }
            catch (e:Exception){
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        "Failed to save Task ${e.message}", SnackbarDuration.Long))
            }
        }
    }


    private fun deleteTask()
    {
        viewModelScope.launch {
            try {
                val currentTaskId = state.value.currentTaskId

                if (currentTaskId!=null)
                {
                    withContext(Dispatchers.IO){
                        taskRepository.deleteTaskById(currentTaskId)
                        _snackbarEventFlow.emit(
                            SnackbarEvent.ShowSnackbar("Task Deleted Successfully")
                        )
                        _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                    }

                }

                else{
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar("Task not found")
                    )
                }

            }
            catch (e:Exception)
            {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar("Failed to delete task. ${e.message}",
                        duration = SnackbarDuration.Long)

                )
            }

        }
    }

    private fun fetchTaskById() {
        viewModelScope.launch {
            navArgs.taskId?.let {
    taskRepository.getTaskByTaskId(it)?.let { task ->
        _state.update {
            it.copy(
                title =task.title,
                description = task.description,
                dueDate = task.dueDate,
                isTaskComplete = task.isCompleted,
                priority = Priority.fromInt(task.priority),
                relatedToSubject = task.relatedToSubject,
                currentTaskId = task.TaskId,
                subjectId = task.taskSubjectId,
                dueTime = task.dueTime,
                isReminderSet = task.setReminder
            )
          }
        }
      }
    }
  }

    private fun fetchSubject(){
        viewModelScope.launch {
            navArgs.subjectId?.let { id->
                subjectRepository
                    .getSubjectById(navArgs.subjectId)?.let { subject->
                        _state.update {
                            it.copy(
                                subjectId = subject.SubjectId,
                                relatedToSubject = subject.name
                            )
                        }
                    }
            }
        }
    }



}
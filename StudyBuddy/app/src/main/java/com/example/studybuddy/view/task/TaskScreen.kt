package com.example.studybuddy.view.task

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.widget.TimePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studybuddy.R
import com.example.studybuddy.utils.Priority
import com.example.studybuddy.utils.SnackbarEvent
import com.example.studybuddy.utils.changeMillisToDateString
import com.example.studybuddy.utils.millisToTimeString
import com.example.studybuddy.view.components.CustomToggleButton
import com.example.studybuddy.view.components.DeleteDialog
import com.example.studybuddy.view.components.SubjectListDropDown
import com.example.studybuddy.view.components.TaskCheckBox
import com.example.studybuddy.view.components.TaskDatePicker
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


data class TaskScreenNavArgs(
    val taskId:Int?,
    val subjectId:Int?
)


@Destination(navArgsDelegate = TaskScreenNavArgs::class)
@Composable
fun TaskScreenRoute(
    navigator:DestinationsNavigator
){

    val viewModel :TaskViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    TaskScreen(
        state =state ,
        onEvent = viewModel::onEvent,
        snackbarEvent = viewModel.snackbarEventFlow,
        onBackButtonClick = {navigator.navigateUp()}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreen(
    state: TaskState,
    snackbarEvent: SharedFlow<SnackbarEvent>,
    onEvent: (TaskEvent) -> Unit,
    onBackButtonClick: () -> Unit
)
{

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isSubjectDropDownOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var isTaskDatePickerOpen by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    var isDeleteTaskDialogOpen by rememberSaveable { mutableStateOf(false) }
    var taskTitleError by remember{mutableStateOf<String?>(null)}
    var subjectTitle by remember { mutableStateOf("") }
    val snackbarHostState = remember{ SnackbarHostState() }
    var isTaskTimePickerOpen by rememberSaveable { mutableStateOf(false) }
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val minute = Calendar.getInstance().get(Calendar.MINUTE)
    var isAlarmSet by remember { mutableStateOf(false) }

    if (isTaskTimePickerOpen) {
        TimePickerDialog(
            context,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                }
                onEvent(TaskEvent.OnTimeChanged(calendar.timeInMillis))
                isTaskTimePickerOpen = false
            },
            hour,
            minute,
            false
        ).show()
    }
    LaunchedEffect(key1 = true) {
        snackbarEvent.collectLatest { event ->
            when (event) {
                is SnackbarEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = event.duration
                    )
                }

                SnackbarEvent.NavigateUp -> onBackButtonClick()
            }
        }
    }

    taskTitleError = when{
        state.title.isBlank() -> "Please enter task title"
        state.title.length > 30 -> "Task title cannot be more than 30 characters"
       state.title.length<4 -> "Task title cannot be less than 4 characters"

        else-> null
    }

    DeleteDialog(
        title = "Delete Task?",
        bodyText = "Are you sure you want to delete this task? "+
                " This action is irreversible",
        isOpen = isDeleteTaskDialogOpen ,
        onDismiss = {isDeleteTaskDialogOpen = false },
        onConfirmBtnClick = {onEvent(TaskEvent.DeleteTask)
            isDeleteTaskDialogOpen = false}
    )

    TaskDatePicker(
        state = datePickerState,
        isOpen =isTaskDatePickerOpen ,
        onDismissRequest = { isTaskDatePickerOpen=false},
        onConfirmRequest = {
            onEvent(TaskEvent.OnDateChanged(millis=datePickerState.selectedDateMillis))
            isTaskDatePickerOpen = false }
    )



    SubjectListDropDown(
        sheetState =sheetState ,
        isOpen = isSubjectDropDownOpen,
        subjects = state.subjects ,
        onDismissRequest = {isSubjectDropDownOpen=false},
        onSubjectClick ={subject ->
            isSubjectDropDownOpen = false
            subjectTitle = subject.name

            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    isSubjectDropDownOpen = false
                }
                onEvent(TaskEvent.OnRelatedSubjectSelect(subject))
            }
        }
    )


    Scaffold(
    topBar = {
        TaskScreenTopBar(
            isTaskExist = state.currentTaskId!=null,
            isCompleted = state.isTaskComplete,
            checkBoxBorderColor = state.priority.color,
            onBackButtonClick = onBackButtonClick,
            onCheckBoxClick = {onEvent(TaskEvent.OnIsCompletedChanged)},
            onDeleteButtonClick = {isDeleteTaskDialogOpen = true}
        )
    },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
) {paddingValues ->

    Column (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        .padding(paddingValues)
        .padding(horizontal = 12.dp)
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.title,
            onValueChange = {onEvent(TaskEvent.OnTitleChanged(it))},
            label = { Text(text = "Title")},
            singleLine = true,
            isError = taskTitleError != null && state.title.isNotBlank(),
            supportingText = { Text(text = taskTitleError.orEmpty())}
        )


        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.description,
            onValueChange ={onEvent(TaskEvent.OnDescriptionChanged(it))},
            label = { Text(text = "Description")},
            )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Due Date",
            style = MaterialTheme.typography.bodySmall
        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = state.dueDate.changeMillisToDateString(),
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = { isTaskDatePickerOpen = true }) {
                Icon(imageVector = Icons.Default.DateRange,
                    contentDescription ="Select Due Date" )

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Due Time",
            style = MaterialTheme.typography.bodySmall
        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = millisToTimeString(state.dueTime),
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = { isTaskTimePickerOpen = true }) {
                Icon(painter = painterResource(id = R.drawable.baseline_add_alarm_24),
                    contentDescription ="Select Due Time" )

            }

        }


        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Set Alarm",
            style = MaterialTheme.typography.bodySmall
        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Do you want to set alarm",
                style = MaterialTheme.typography.bodyLarge
            )
            CustomToggleButton(
                checked = state.isReminderSet,
                onCheckedClick = {
                    onEvent(TaskEvent.onSetReminderchanged(it))
                    isAlarmSet = state.isReminderSet
                }
            )

        }


        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Priority",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(5.dp)),) {
            Priority.entries.forEach{
                PriorityButton(
                    modifier = Modifier.weight(1f),
                    label = it.name,
                    backgroundColor = it.color,
                    borderColor = if (it == state.priority){
                        Color.White
                    } else Color.Transparent,
                    labelColor = if (it == state.priority){
                        Color.White
                    } else Color.White.copy(alpha = 0.7f),
                    onClick = {onEvent(TaskEvent.OnPriorityChanged(it))}
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Related to subject",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val firstSubject = state.subjects.firstOrNull()?.name ?: ""
            Text(text = state.relatedToSubject?:firstSubject,
                style = MaterialTheme.typography.bodyLarge
            )

            IconButton(onClick = { isSubjectDropDownOpen=true }) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription =state.relatedToSubject )

            }

        }

        Button(
            enabled = taskTitleError==null,
            onClick = {
                      if (state.isReminderSet){
                          setAlarm(context,state.dueDate!!,state.dueTime!!,state.title,state.currentTaskId!!)
                      }
                else{
                    cancelAlarm(context,state.currentTaskId!!)
                      }
                onEvent(TaskEvent.SaveTask)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text(text = "Save")

        }



    }
}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreenTopBar(
    isTaskExist: Boolean,
    isCompleted: Boolean,
    checkBoxBorderColor: Color,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onCheckBoxClick: () -> Unit
)
{
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription ="Navigate Back" )
            }
        },
        title = {
            Text(text = "Task")
        },
        actions = {
            if (isTaskExist)
            {
                TaskCheckBox(isCompleted = isCompleted,
                    borderColor = checkBoxBorderColor,
                    onCheckBoxClicked = onCheckBoxClick
                )
                IconButton(onClick = onDeleteButtonClick ) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription ="Delete Task" )
                }
            }
        }
    )
}


@Composable
private fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    borderColor: Color,
    labelColor: Color,
    onClick: () -> Unit
)
{
    Box(modifier = modifier
        .background(backgroundColor)
        .clickable { onClick() }
        .padding(5.dp)
        .border(1.dp, borderColor, RoundedCornerShape(5.dp))
        .padding(5.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = label,
            color = labelColor
        )
    }

}


fun setAlarm(context: Context, dueDate: Long, dueTime: Long, taskTitle: String, taskId: Int) {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = dueDate
        val dueTimeCalendar = Calendar.getInstance().apply {
            timeInMillis = dueTime
        }
        set(Calendar.HOUR_OF_DAY, dueTimeCalendar.get(Calendar.HOUR_OF_DAY))
        set(Calendar.MINUTE, dueTimeCalendar.get(Calendar.MINUTE))
        set(Calendar.SECOND, 0)
    }

    val timeInMillis = calendar.timeInMillis - (5 * 60 * 1000) // Set alarm 5 minutes before the due date and time

    Toast.makeText(context, "Alarm set for $timeInMillis", Toast.LENGTH_SHORT).show()
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java).apply {
        putExtra("taskTitle", taskTitle)
    }
    val pendingIntent = PendingIntent.getBroadcast(context, taskId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

    alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
}

private fun cancelAlarm(context: Context, taskId: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, taskId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    alarmManager.cancel(pendingIntent)
}



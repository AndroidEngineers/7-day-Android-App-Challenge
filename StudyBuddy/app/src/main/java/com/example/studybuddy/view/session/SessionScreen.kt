package com.example.studybuddy.view.session

import android.content.Intent
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studybuddy.ui.theme.Red
import com.example.studybuddy.utils.ServiceConstants.Action_Service_Cancel
import com.example.studybuddy.utils.ServiceConstants.Action_Service_Start
import com.example.studybuddy.utils.ServiceConstants.Action_Service_Stop
import com.example.studybuddy.utils.SnackbarEvent
import com.example.studybuddy.view.components.DeleteDialog
import com.example.studybuddy.view.components.StudySessionList
import com.example.studybuddy.view.components.SubjectListDropDown
import com.example.studybuddy.view.destinations.MainScreenRouteDestination
import com.example.studybuddy.view.destinations.SessionScreenRouteDestination
import com.example.studybuddy.view.destinations.SubjectScreenRouteDestination
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit

@Destination(
    deepLinks = [
        DeepLink(
            action = Intent.ACTION_VIEW,
            uriPattern = "study_buddy://dashboard/session"
        )
    ]
)
@Composable
fun SessionScreenRoute(
    navigator: DestinationsNavigator,
    timerService: StudySessionTimerService
)
{
    val viewModel:SessionScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SessionScreen(
        state =state ,
        onEvent = viewModel::onEvent ,
        onBackButtonClick = {
            navigator.navigate(MainScreenRouteDestination(0)) {
                popUpTo(SessionScreenRouteDestination) { inclusive = true }
            }
        },
        snackbarEvent = viewModel.snackbarEventFlow,
        timerService = timerService
    )
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
private fun SessionScreen(
    state: SessionState,
    snackbarEvent: SharedFlow<SnackbarEvent>,
    onEvent: (SessionEvent) -> Unit,
    onBackButtonClick: () -> Unit,
    timerService: StudySessionTimerService
)
{
    val hours by timerService.hours
    val minutes by timerService.minute
    val seconds by timerService.second
    val currentTimerState by timerService.currentTimerState


    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isSubjectDropDownOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember{ SnackbarHostState() }


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

    LaunchedEffect(key1 = state.subjects) {
        val subjectId = timerService.subjectId.value
        onEvent(
            SessionEvent.UpdateSubjectIdAndRelatedSubject(
                subjectId = subjectId,
                relatedToSubject = state.subjects.find { it.SubjectId == subjectId }?.name

            )
        )

    }






    DeleteDialog(
        title = "Delete Session?",
        bodyText = "Are you sure you want to delete this session? Your studied hour will be reduced"+
                " by the duration of the session. This action is irreversible",
        isOpen = isDeleteSessionDialogOpen ,
        onDismiss = {isDeleteSessionDialogOpen = false },
        onConfirmBtnClick = {
            onEvent(SessionEvent.DeleteSession)
            isDeleteSessionDialogOpen = false})


    SubjectListDropDown(
        sheetState =sheetState ,
        isOpen = isSubjectDropDownOpen,
        subjects = state.subjects ,
        onDismissRequest = {isSubjectDropDownOpen=false},
        onSubjectClick ={subject ->
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    isSubjectDropDownOpen = false
                }

            }
            onEvent(SessionEvent.OnRelatedSubjectChange(subject))
        }
    )


    Scaffold(
        topBar = {
            SessionScreenTopBar(
                onBackButtonClick = onBackButtonClick,

            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            item {
                TimerSection(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds)

                Text(text = "Related to subject")
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = state.relatedToSubject ?: "Select Subject",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    IconButton(
                        onClick = { isSubjectDropDownOpen=true },
                        enabled = seconds == "00"
                        ) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription ="Select Subject" )

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
            }
            item { 
                TimerButtonSection(
                    modifier = Modifier.fillMaxWidth(),
                    onStartButtonClick = {
                        if (state.subjectId!= null && state.relatedToSubject != null){
                            ServiceHelper.triggerForegroundService(
                                context = context,
                                action = if (currentTimerState == StudySessionTimerService.TimerState.STARTED){
                                    Action_Service_Stop
                                }
                                else Action_Service_Start
                            )
                            timerService.subjectId.value = state.subjectId
                        }
                        else{
                            onEvent(SessionEvent.NotifyToUpdateSubject)
                        }

                    },
                    onCancelButtonClick = {
                        ServiceHelper.triggerForegroundService(
                        context = context,
                        action = Action_Service_Cancel
                    )
                                          },
                    onFinishButtonClick = {
                        val duration = timerService.duration.toLong(DurationUnit.SECONDS)
                        onEvent(SessionEvent.SaveSession(duration))
                        if (duration>= 36)
                        {
                            ServiceHelper.triggerForegroundService(
                                context = context,
                                action = Action_Service_Cancel
                            )
                        }
                    },
                    timerState = currentTimerState,
                    seconds = seconds
                )
                Spacer(modifier = Modifier.height(20.dp))

            }

            StudySessionList(sectionTitle ="RECENT STUDY SESSION" ,
                Sessions = state.sessions ,
                emptyListText ="You don't have any recent study session.\n Start a study session to begin your progress.",
                onDeleteItemClick = {
                    isDeleteSessionDialogOpen=true
                    onEvent(SessionEvent.OnDeleteSessionButtonClicked(it))
                    }
            )

        }

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SessionScreenTopBar(

    onBackButtonClick: () -> Unit,
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
            Text(text = "Session Screen")
        }
    )
}

@Composable
private fun TimerSection
            (
    modifier: Modifier,
    hours:String,
    minutes:String,
    seconds:String
)
{
    Box(modifier = modifier,
        contentAlignment = Alignment.Center)
    {
        Box(
            modifier = Modifier
                .size(250.dp)
                .border(5.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Row {
                AnimatedContent(
                    targetState = hours,
                    label = hours,
                    transitionSpec = { timerTextAnimation()}
                ) {
                    hours ->
                    Text(text = "$hours:",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp))
                }
                AnimatedContent(
                    targetState = minutes,
                    label = minutes,
                    transitionSpec = { timerTextAnimation()}
                ) {
                        minutes ->
                    Text(text = "$minutes:",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp))
                }
                AnimatedContent(
                    targetState = seconds,
                    label = seconds,
                    transitionSpec = { timerTextAnimation()}
                ) {
                        seconds ->
                    Text(text = "$seconds",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp))
                }
            }
            }
    }
}

@Composable
private fun TimerButtonSection(
    modifier: Modifier,
    onStartButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onFinishButtonClick: () -> Unit,
    timerState: StudySessionTimerService.TimerState,
    seconds:String
)
{
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp),
            onClick = onCancelButtonClick,
            enabled = seconds != "00" && timerState != StudySessionTimerService.TimerState.STARTED)
        {
            Text(text = "Cancel")
        }

        Button(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp),
            onClick = onStartButtonClick,

            colors = ButtonDefaults.buttonColors(
                containerColor = if (timerState == StudySessionTimerService.TimerState.STARTED) Red
                else MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )

        ) {
            Text(
                text = when(timerState)
            {

                StudySessionTimerService.TimerState.STARTED -> "Stop"
                StudySessionTimerService.TimerState.STOPPED -> "Resume"
                    else -> "Start"
            })
        }

        Button(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp),
            onClick = { onFinishButtonClick() },
            enabled = seconds != "00" && timerState != StudySessionTimerService.TimerState.STARTED
            ) {
            Text(text = "Finish")
        }
    }
}

private fun timerTextAnimation(duration: Int = 600): ContentTransform {
    return slideInVertically(animationSpec = tween(duration)) { fullHeight -> fullHeight } +
            fadeIn(animationSpec = tween(duration))togetherWith
            slideOutVertically(animationSpec = tween(duration)) { fullHeight -> -fullHeight }+
            fadeOut(animationSpec = tween(duration))
}



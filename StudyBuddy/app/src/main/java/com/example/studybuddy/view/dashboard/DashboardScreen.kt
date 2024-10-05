package com.example.studybuddy.view.dashboard

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studybuddy.R
import com.example.studybuddy.domain.model.BottomNavigationItem
import com.example.studybuddy.domain.model.Session
import com.example.studybuddy.domain.model.Subject
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.utils.SnackbarEvent
import com.example.studybuddy.view.components.AddSubjectDialog
import com.example.studybuddy.view.components.CountCards
import com.example.studybuddy.view.components.DeleteDialog
import com.example.studybuddy.view.components.StudySessionList
import com.example.studybuddy.view.components.SubjectCard
import com.example.studybuddy.view.components.TaskList
import com.example.studybuddy.view.destinations.SessionScreenRouteDestination
import com.example.studybuddy.view.destinations.SubjectScreenRouteDestination
import com.example.studybuddy.view.destinations.TaskScreenRouteDestination
import com.example.studybuddy.view.subject.SubjectScreenNavArgs
import com.example.studybuddy.view.task.TaskScreenNavArgs
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Destination(
    deepLinks = [
        DeepLink(action = Intent.ACTION_VIEW, uriPattern = "study_buddy://dashboard")
    ]
)
@Composable
fun DashBoardScreenRoute(
    navigator: DestinationsNavigator
){
    val viewModel : DashboardViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val task by viewModel.tasks.collectAsStateWithLifecycle()
    val session by viewModel.session.collectAsStateWithLifecycle()

    DashboardScreen(
        state = state,
        task = task,
        session = session,
        onEvent = viewModel::onEvent,
        snackbarEvent = viewModel.snackbarEventFlow,
        onTaskCardClick ={taskId ->
                         taskId?.let{
                             val navArgs = TaskScreenNavArgs(taskId, subjectId = null)
                             navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
                         }
        } ,
        onSubjectCardClick ={
                subjectId ->
            subjectId?.let {
                val navArgs = SubjectScreenNavArgs(subjectId)
                navigator.navigate(SubjectScreenRouteDestination(navArgs))
            }
        } ,
        onStartSessionButtonClick ={
            navigator.navigate(SessionScreenRouteDestination())
        } )
}


@Composable
private fun DashboardScreen(
    state: dashboardVariable,
    task:List<Task>,
    session:List<Session>,
    onEvent: (DashboardEvent) -> Unit,
    snackbarEvent: SharedFlow<SnackbarEvent>,
    onTaskCardClick: (Int?) -> Unit,
    onSubjectCardClick: (Int?) -> Unit,
    onStartSessionButtonClick: () -> Unit,
){


    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
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

                SnackbarEvent.NavigateUp -> {}
                }
            }
        }

    AddSubjectDialog(
        subjectName = state.subjectName,
        goalHours = state.goalStudyHours,
        onSubjectNameChange ={onEvent.invoke(DashboardEvent.OnSubjectNameChange(it))},
        onGoalHoursChange ={onEvent.invoke(DashboardEvent.OnGoalStudyHoursChange(it))} ,
        selectedColor = state.subjectCardColors,
        onColourChange ={onEvent.invoke(DashboardEvent.OnSubjectCardColorChange(it))} ,
        isOpen = isAddSubjectDialogOpen ,
        onDismiss = {isAddSubjectDialogOpen = false },
        onConfirmBtnClick = {onEvent.invoke(DashboardEvent.SaveSubject)
            isAddSubjectDialogOpen = false})

    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    DeleteDialog(
        title = "Delete Session?",
        bodyText = "Are you sure you want to delete this session? Your studied hour will be reduced"+
        " by the duration of the session. This action is irreversible",
        isOpen = isDeleteDialogOpen ,
        onDismiss = {isDeleteDialogOpen = false },
        onConfirmBtnClick = {onEvent.invoke(DashboardEvent.DeleteSession)
            isDeleteDialogOpen = false})


    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        topBar = { DashboardTopBar() }
    ){paddingValues ->
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)){
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = state.totalSubjectCount ,
                    studyHours =state.totalStudiedHours.toString() ,
                    targetHours = state.totalGoalHours.toString())
            }
            item{
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = state.subjects,
                    onAddIconClick = {isAddSubjectDialogOpen = true},
                    onSubjectCardClick = onSubjectCardClick
                )
            }

            item {
                Button(onClick = onStartSessionButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp),
                    enabled = state.subjects.isNotEmpty()
                ) {
                    Text(text = "Start Study Session")
                }
            }
            TaskList(sectionTitle ="UPCOMING TASKS" ,
                Tasks = task,
                emptyListText ="You don't have any task yet.\n Click + button to add new task.",
                onCheckBoxClick = {onEvent.invoke(DashboardEvent.OnTaskIsCompleteChange(it))},
                onTaskCardClick = onTaskCardClick
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            StudySessionList(sectionTitle ="RECENT STUDY SESSION" ,
                Sessions = session ,
                emptyListText ="You don't have any recent study session.\n Start a study session to begin your progress.",
                onDeleteItemClick = {onEvent.invoke(DashboardEvent.OnDeleteSessionButtonClicked(it))
                    isDeleteDialogOpen=true}
            )

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(){
    CenterAlignedTopAppBar(title = {
        Text(text = "StudyBuddy",
            style = MaterialTheme.typography.headlineMedium)
    })
}

@Composable
fun BottomAppBar(){
   NavigationBar {

   }
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studyHours: String,
    targetHours: String
){
    Row {
        CountCards(
            modifier = modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCards(
            modifier = modifier.weight(1f),
            headingText = "Study Hours",
            count = studyHours
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCards(
            modifier = modifier.weight(1f),
            headingText = "Target Hours",
            count = targetHours
        )


    }
}

@Composable
private fun SubjectCardSection(
    modifier: Modifier,
    subjectList:List<Subject>,
    emptyListText:String = "You don't have any subject yet.\n Click + button to add new subject." ,
    onAddIconClick: () -> Unit,
    onSubjectCardClick: (Int?) -> Unit
){
    Column(modifier = modifier) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "SUBJECTS",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodySmall
            )

            IconButton(onClick = onAddIconClick ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription ="Add Subjects" )

            }

        }
        if (subjectList.isEmpty()){
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally) ,
                painter = painterResource(id = R.drawable.books),
                contentDescription =emptyListText )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ){
            items(subjectList){subject ->
                SubjectCard(
                    subjectName = subject.name,
                    gradientColors =subject.color.map { Color(it) },
                    onClick = {onSubjectCardClick(subject.SubjectId)})

                }

            }
        }
    }


@Preview
@Composable
fun BottomAppBarPreview() {
    BottomAppBar()
}



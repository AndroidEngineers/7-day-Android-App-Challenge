package com.example.studybuddy.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.studybuddy.R
import com.example.studybuddy.domain.model.Task
import com.example.studybuddy.utils.Priority
import com.example.studybuddy.utils.changeMillisToDateString

fun LazyListScope.TaskList(
    sectionTitle:String,
    Tasks:List<Task>,
    emptyListText:String,
    onCheckBoxClick:(Task)->Unit,
    onTaskCardClick:(Int?)->Unit
){
    item {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = sectionTitle,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodySmall)

                Text(text = "See All",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodySmall)

            }


        }
    }
    if (Tasks.isEmpty()){
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp) ,
                    painter = painterResource(id = R.drawable.todo),
                    contentDescription =emptyListText )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = emptyListText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    items(Tasks){task->
        TaskCard(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            task =task ,
            onCheckBoxClicked = { onCheckBoxClick(task) },
            onClick = {onTaskCardClick(task.TaskId)}
        )

    }

}


@Composable
private fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onCheckBoxClicked: () -> Unit,
    onClick: () -> Unit
)
{
    ElevatedCard(modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskCheckBox(
                isCompleted = task.isCompleted,
                borderColor = Priority.fromInt(task.priority).color,
                onCheckBoxClicked = onCheckBoxClicked,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isCompleted) {
                        TextDecoration.LineThrough
                    }
                else TextDecoration.None)

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = task.dueDate.changeMillisToDateString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray)
            }
        }

    }

}
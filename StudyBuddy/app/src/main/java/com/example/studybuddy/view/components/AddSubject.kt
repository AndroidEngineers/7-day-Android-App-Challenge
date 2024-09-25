package com.example.studybuddy.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.studybuddy.domain.model.Subject

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title:String = "Add/Update Subject",
    selectedColor: List<Color>,
    subjectName:String,
    goalHours:String,
    onColourChange:(List<Color>) ->Unit,
    onSubjectNameChange:(String) ->Unit,
    onGoalHoursChange:(String) ->Unit,
    onDismiss: () -> Unit,
    onConfirmBtnClick: () -> Unit
){
    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null)}
    var goalHoursError by rememberSaveable { mutableStateOf<String?>(null)}


    subjectNameError = when{
        subjectName.isBlank()-> "Please enter subject name"
        subjectName.length < 2 -> "Subject name must be at least 2 characters"
        subjectName.length >20 -> "Subject name must be less than 20 characters"

        else -> null
    }

    goalHoursError = when{
        goalHours.isBlank()-> "Please enter goal study hours"
        goalHours.toFloatOrNull()==null -> "Please enter a valid number"
        goalHours.toFloat()<=0f -> "Please enter a number greater than 0"
        goalHours.toFloat()>1000f -> "Please enter a number less than 1000"

        else -> null
    }

    if (isOpen){
       AlertDialog(
           title = { Text(text=title) },
           onDismissRequest = onDismiss,
           text = {
               Column() {
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(bottom = 16.dp),
                       horizontalArrangement = Arrangement.SpaceAround
                   ) {
                       Subject.subjectCardColor.forEach {
                               colors ->
                           Box(
                               modifier = Modifier
                                   .size(24.dp)
                                   .clip(CircleShape)
                                   .border(
                                       width = 1.dp,
                                       color = if (colors == selectedColor) Color.Black
                                       else Color.Transparent,
                                       shape = CircleShape
                                   )
                                   .background(brush = Brush.verticalGradient(colors))
                                   .clickable { onColourChange(colors) }


                           )

                       }



                   }
                   OutlinedTextField(
                       value =subjectName,
                       onValueChange =onSubjectNameChange,
                       label = { Text(text = "Subject Name")},
                       singleLine = true,
                       isError = subjectNameError != null && subjectName.isNotBlank(),
                       supportingText = { Text(text = subjectNameError.orEmpty())}
                   )

                   Spacer(modifier = Modifier.height(8.dp))

                   OutlinedTextField(
                       value =goalHours ,
                       onValueChange =onGoalHoursChange,
                       label = { Text(text = "Goal Study Hours")},
                       singleLine = true,
                       isError = goalHoursError != null && goalHours.isNotBlank(),
                       supportingText = { Text(text = goalHoursError.orEmpty())},
                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
               }

           },
           confirmButton = {
               TextButton(onClick = onConfirmBtnClick,
                   enabled = subjectNameError == null && goalHoursError == null) {
                   Text(text = "Save")
               }

           },
           dismissButton = {
               TextButton(onClick = onDismiss) {
                   Text(text = "Close")
               }

           }

       )
   }
}
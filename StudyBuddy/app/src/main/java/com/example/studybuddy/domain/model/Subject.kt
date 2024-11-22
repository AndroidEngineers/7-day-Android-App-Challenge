package com.example.studybuddy.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.studybuddy.ui.theme.gradient1
import com.example.studybuddy.ui.theme.gradient2
import com.example.studybuddy.ui.theme.gradient3
import com.example.studybuddy.ui.theme.gradient4
import com.example.studybuddy.ui.theme.gradient5

@Entity
data class Subject(
    val name: String,
    val goalHour:Float,
    val color:List<Int>,
    @PrimaryKey(autoGenerate = true)
   val SubjectId:Int? = null
){
    companion object{
        val subjectCardColor = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)

    }
}

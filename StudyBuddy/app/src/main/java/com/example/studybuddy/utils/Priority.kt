package com.example.studybuddy.utils

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import com.example.studybuddy.ui.theme.Green
import com.example.studybuddy.ui.theme.Orange
import com.example.studybuddy.ui.theme.Red
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

enum class Priority(val title: String, val color:androidx.compose.ui.graphics.Color, val value: Int) {
    LOW(title = "Low", color = Green, value = 1),
    MEDIUM(title = "Medium", color = Orange, value = 2),
    HIGH(title = "High", color = Red, value = 3);


    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value }?:MEDIUM
    }


}


fun Long?.changeMillisToDateString():String{
    val date:LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate()

    }?: LocalDate.now()

    return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

fun millisToTimeString(millis: Long?, pattern: String = "hh:mm a"): String {
    return millis?.let {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        formatter.format(Date(it))
    } ?: "No Time Set"
}



fun Long.toHours():Float{
    val second = this.toFloat()
    val hours = second.div(3600)
    return "%.2f".format(hours).toFloat()
}




sealed class SnackbarEvent{
    data class ShowSnackbar(
        val message:String,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ):SnackbarEvent()

    data object NavigateUp:SnackbarEvent()

    fun Int.TimeToString():String{
        return this.toString().padStart(length = 2, padChar = '0')
    }
}

fun handleException(tag: String, message: String, exception: Exception?) {
    // Log the error
    Log.e(tag, message, exception)

    // Optionally, you can show a snackbar or toast
    SnackbarEvent.ShowSnackbar("Error: ${exception?.localizedMessage ?: "Unknown error"}")
}
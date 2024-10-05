package com.example.studybuddy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.UUID


@HiltAndroidApp
class StudyBuddyApp:Application(){
    companion object{
        val username = UUID.randomUUID().toString().substring(0,6)
    }
}
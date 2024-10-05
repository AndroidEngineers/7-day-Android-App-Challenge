package com.example.studybuddy.view.session

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import com.example.studybuddy.utils.ServiceConstants.Action_Service_Cancel
import com.example.studybuddy.utils.ServiceConstants.Action_Service_Start
import com.example.studybuddy.utils.ServiceConstants.Action_Service_Stop
import com.example.studybuddy.utils.ServiceConstants.NOTIFICATION_CHANNEL_ID
import com.example.studybuddy.utils.ServiceConstants.NOTIFICATION_CHANNEL_NAME
import com.example.studybuddy.utils.ServiceConstants.NOTIFICATION_ID
import com.example.studybuddy.utils.SnackbarEvent.NavigateUp.TimeToString
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.seconds


@AndroidEntryPoint
class StudySessionTimerService : Service() {

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    private val serviceBinder = StudySessionTimerBinder()

    private lateinit var timer: Timer
    var duration: Duration = Duration.ZERO
    var second = mutableStateOf("00")
    private set

    var minute = mutableStateOf("00")
        private set

    var hours = mutableStateOf("00")
        private set

    var currentTimerState = mutableStateOf(TimerState.IDLE)
    private set

    var subjectId = mutableStateOf<Int?>(null)

    override fun onBind(intent: Intent?) = serviceBinder
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.action.let {
            when (it) {
                Action_Service_Start -> {
                    startForegroundService()
                    StartTimer{hours,minute,second ->
                        updateNotification(hours,minute,second)
                    }
                }


                Action_Service_Stop -> {
                    stopTimer()
                }

                Action_Service_Cancel -> {
                    stopTimer()
                    cancelTimer()
                    stopForegroundService()
                }
            }
            return super.onStartCommand(intent, flags, startId)
        }
    }



    private fun startForegroundService(){
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,notificationBuilder.build())
    }

    private fun createNotificationChannel(){
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun updateNotification(hours:String,minute:String,second:String){
        notificationManager.notify(NOTIFICATION_ID,
            notificationBuilder.setContentText("$hours:$minute:$second")
                .build()
        )
    }

    private fun StartTimer(
        onTick:(h:String,m:String,s:String) ->Unit
    )
    {
        currentTimerState.value = TimerState.STARTED
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L){
            duration = duration.plus(1.seconds)
            updateTimeUnits()
            onTick(hours.value,minute.value,second.value)
        }
    }
    
    private fun updateTimeUnits(){
        duration.toComponents { hours, minutes, seconds,_->
            this@StudySessionTimerService.hours.value =hours.toInt().TimeToString()
            this@StudySessionTimerService.minute.value = minutes.TimeToString()
            this@StudySessionTimerService.second.value = seconds.TimeToString()
        }


    }

    inner class StudySessionTimerBinder : Binder(){
        fun getService(): StudySessionTimerService = this@StudySessionTimerService
    }

    private fun stopTimer(){
        if (this::timer.isInitialized){
            timer.cancel()
        }
        currentTimerState.value = TimerState.STOPPED
    }

    private fun cancelTimer(){
        duration = ZERO
        updateTimeUnits()
        currentTimerState.value = TimerState.IDLE
    }
    private fun stopForegroundService(){
        notificationManager.cancel(NOTIFICATION_ID)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()

    }

    enum class TimerState{
        IDLE,
        STARTED,
        STOPPED
    }
}
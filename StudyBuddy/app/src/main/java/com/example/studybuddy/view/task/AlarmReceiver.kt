package com.example.studybuddy.view.task

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Handler
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.studybuddy.MainActivity
import kotlin.random.Random

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private var ringtone: Ringtone? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.example.studybuddy.STOP_ALARM") {
            stopAlarm()
        } else {
            try {
                val taskId = intent?.getIntExtra("task_id", -1)
                val taskTitle = intent?.getStringExtra("taskTitle")
                Toast.makeText(context, "Reminder for task: $taskTitle", Toast.LENGTH_SHORT).show()
                showNotification(context, taskTitle, taskId)
                ringAlarm(context)
            } catch (e: Exception) {
                Toast.makeText(context, "Error on receiver: $e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showNotification(context: Context?, taskTitle: String?, taskId: Int?) {
        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random.nextInt()
        val channelId = "reminder_channel"
        val channelName = "Reminder Channel"

        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        val stopAlarmIntent = Intent(context, this.javaClass).apply {
            action = "com.example.studybuddy.STOP_ALARM" // Unique action string
            putExtra("notification_id", notificationId)
        }
        val stopAlarmPendingIntent = PendingIntent.getBroadcast(context, 0, stopAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("task_id", taskId)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Reminder")
            .setContentText("Task: $taskTitle")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.ic_media_pause, "Stop Alarm", stopAlarmPendingIntent)
            .setAutoCancel(true)

        manager.notify(notificationId, builder.build())
    }

    private fun ringAlarm(context: Context?) {
        ringtone = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
        ringtone?.play()

        Handler().postDelayed({ stopAlarm() }, 20000) // Stop after 20 seconds
    }

    private fun stopAlarm() {
        ringtone?.stop()
    }
}

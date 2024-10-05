package com.example.studybuddy.view.videocall.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.studybuddy.view.videocall.CloseActivity


class CallBroadcastReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.action?.let { action ->
            if (action == "ACTION_EXIT") {
                context?.let { noneNullContext ->
                    CallService.stopService(noneNullContext) // Stop the service
                    noneNullContext.startActivity(
                        Intent(noneNullContext, CloseActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    )
                }
            }
        }
    }
}

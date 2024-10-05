package com.example.studybuddy.view.videocall.webrtc

import com.example.studybuddy.domain.model.MessageModel


interface WebRTCSignalListener {
    fun onTransferEventToSocket(data: MessageModel)

}
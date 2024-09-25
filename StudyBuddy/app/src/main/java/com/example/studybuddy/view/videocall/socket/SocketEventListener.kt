package com.example.studybuddy.view.videocall.socket

import com.example.studybuddy.domain.model.MessageModel

interface SocketEventListener {
    fun onNewMessage(message: MessageModel)
    fun onSocketOpened()
    fun onSocketClosed()
}
package com.example.studybuddy.domain.model

import com.example.studybuddy.view.videocall.socket.SocketEvents

data class MessageModel(
    val type: SocketEvents,
    val name: String? = null,
    val target: String? = null,
    val data:Any?=null
)
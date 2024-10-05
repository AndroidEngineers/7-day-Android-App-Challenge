package com.example.studybuddy.view.videocall.socket


import com.example.studybuddy.StudyBuddyApp
import com.example.studybuddy.domain.model.MessageModel
import javax.inject.Inject

class SocketEventSender @Inject constructor(
    private val socketClient: SocketClient
) {

    private var username = StudyBuddyApp.username

    fun storeUser() {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.StoreUser, name = username)
        )
    }

    fun createRoom(roomName: String) {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.CreateRoom, data = roomName, name = username)
        )
    }

    fun joinRoom(roomName: String) {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.JoinRoom, data = roomName, name = username)
        )
    }

    fun leaveAllRooms() {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.LeaveAllRooms, name = username)
        )
    }

    fun startCall(target: String) {
        socketClient.sendMessageToSocket(
            MessageModel(type = SocketEvents.StartCall, name = username, target = target)
        )
    }
}
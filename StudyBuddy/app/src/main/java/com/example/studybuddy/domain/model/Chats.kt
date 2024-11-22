package com.example.studybuddy.domain.model

data class ChatData(
    val chatId: String? = "",
    val user1:ChatUser = ChatUser(),
    val user2:ChatUser = ChatUser(),
)

data class ChatUser(
    val userId: String? = "",
    val name: String? = "",
    val phone: String? = "",
)

data class Message(
    val userId: String="",
    val time:String="",
    val message:String=""
)


package com.example.studybuddy.view.chat

import android.icu.util.Calendar
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.studybuddy.domain.model.ChatData
import com.example.studybuddy.domain.model.ChatUser
import com.example.studybuddy.domain.model.Message
import com.example.studybuddy.domain.model.UserData
import com.example.studybuddy.utils.Chat_Data
import com.example.studybuddy.utils.MESSAGE
import com.example.studybuddy.utils.User_Node
import com.example.studybuddy.utils.handleException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.firestore.util.Listener

class ChatListViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val userData = mutableStateOf<UserData?>(null)
    val isLoading = mutableStateOf(false)
    val chats = mutableStateOf<List<ChatData>>(listOf())
    val chatMessage = mutableStateOf<List<Message>>(listOf())
    val inProgressChat = mutableStateOf(false)
    var currentChatMessageListener: ListenerRegistration? = null

    init {
        Log.d("ChatListViewModel", "Initializing ViewModel")
        fetchUserData()
    }

    private fun fetchUserData() {
        Log.d("ChatListViewModel", "Fetching user data")
        val uid = auth.currentUser?.uid
        if (uid != null) {
            isLoading.value = true
            firestore.collection(User_Node).document(uid).addSnapshotListener { value, error ->
                if (error != null) {
                    Log.d("ChatListViewModel", "Error fetching user data: ${error.message}")
                    handleException("ChatListViewModel", "Something went wrong", error)
                }
                if (value != null) {
                    Log.d("ChatListViewModel", "User data fetched successfully")
                    userData.value = value.toObject<UserData>()
                    isLoading.value = false
                    populateChats()
                }
            }
        } else {
            Log.d("ChatListViewModel", "User is not signed in")
            handleException("ChatListViewModel", "User is not signed in", null)
        }
    }

    fun populateChats() {
        Log.d("ChatListViewModel", "Populating chats")
        val userId = userData.value?.userId ?: return
        isLoading.value = true
        firestore.collection(Chat_Data).where(
            Filter.or(
                Filter.equalTo("user1.userId", userId),
                Filter.equalTo("user2.userId", userId)
            )
        ).addSnapshotListener { value, error ->
            if (error != null) {
                Log.d("ChatListViewModel", "Error fetching chats: ${error.message}")
                handleException("ChatListViewModel", "Something went wrong", error)
            }
            if (value != null) {
                Log.d("ChatListViewModel", "Chats populated successfully")
                chats.value = value.documents.mapNotNull {
                    it.toObject<ChatData>()
                }
                isLoading.value = false
            } else {
                Log.d("ChatListViewModel", "No chats found")
                handleException("ChatListViewModel", "Something went wrong", error)
                isLoading.value = false
            }
        }
    }

    fun onAddChat(number: String) {
        Log.d("ChatListViewModel", "Adding chat for number: $number")
        val userId = userData.value?.userId ?: return
        firestore.collection(Chat_Data).where(
            Filter.or(
                Filter.and(
                    Filter.equalTo("user1.phone", number),
                    Filter.equalTo("user2.phone", userData.value?.number)
                ),
                Filter.and(
                    Filter.equalTo("user1.phone", userData.value?.number),
                    Filter.equalTo("user2.phone", number)
                )
            )
        ).get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty) {
                Log.d("ChatListViewModel", "No existing chat found, creating new chat")
                firestore.collection(User_Node).whereEqualTo("number", number).get()
                    .addOnSuccessListener { userSnapshot ->
                        if (userSnapshot.isEmpty) {
                            Log.d("ChatListViewModel", "Number not found")
                            handleException("ChatListViewModel", "Number not found", null)
                        } else {
                            val chatPartner = userSnapshot.toObjects<UserData>()[0]
                            val id = firestore.collection(Chat_Data).document().id
                            val chat = ChatData(
                                chatId = id,
                                ChatUser(
                                    userId,
                                    userData.value?.name,
                                    userData.value?.number
                                ),
                                ChatUser(
                                    chatPartner.userId,
                                    chatPartner.name,
                                    chatPartner.number
                                )
                            )
                            firestore.collection(Chat_Data).document(id).set(chat)
                            Log.d("ChatListViewModel", "Chat created with partner ${chatPartner.number}")
                        }
                    }
                    .addOnFailureListener {
                        Log.d("ChatListViewModel", "Error fetching user data: ${it.message}")
                    }
            } else {
                Log.d("ChatListViewModel", "Chat already exists")
                handleException("ChatListViewModel", "Chat already exists", null)
            }
        }
            .addOnFailureListener {
                Log.d("ChatListViewModel", "Error checking existing chats: ${it.message}")
            }
    }

    fun getMessages(chatId: String) {
        Log.d("ChatListViewModel", "Getting messages for chatId: $chatId")
        inProgressChat.value = true
        currentChatMessageListener = firestore.collection(Chat_Data).document(chatId).collection(
            MESSAGE).addSnapshotListener { value, error ->
            if (error != null) {
                Log.d("ChatListViewModel", "Error fetching messages: ${error.message}")
                handleException("ChatListViewModel", "Something went wrong", error)
            }
            if (value != null) {
                Log.d("ChatListViewModel", "Messages fetched successfully")
                chatMessage.value = value.documents.mapNotNull {
                    it.toObject<Message>()
                }.sortedBy { it.time }
                inProgressChat.value = false
            }
        }
    }

    fun depopulateMessage() {
        Log.d("ChatListViewModel", "Depopulating messages")
        chatMessage.value = listOf()
        currentChatMessageListener?.remove()
        currentChatMessageListener = null
    }

    fun onSendMessage(chatId: String, message: String) {
        Log.d("ChatListViewModel", "Sending message to chatId: $chatId")
        val time = Calendar.getInstance().time.toString()
        val msg = Message(userId = userData.value?.userId ?: return, time = time, message = message)
        firestore.collection(Chat_Data)
            .document(chatId)
            .collection(MESSAGE)
            .document()
            .set(msg)
            .addOnSuccessListener {
                Log.d("ChatListViewModel", "Message sent successfully")
            }
            .addOnFailureListener {
                Log.d("ChatListViewModel", "Failed to send message: ${it.message}")
            }
    }
}


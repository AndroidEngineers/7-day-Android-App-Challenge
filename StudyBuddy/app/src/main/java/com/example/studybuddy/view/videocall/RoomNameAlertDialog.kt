package com.example.studybuddy.view.videocall

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast

class RoomNameAlertDialog(
    private val context: Context,
    private val listener: RoomNameDialogListener
) {

    fun show() {
        // Create an EditText for the room name input
        val input = EditText(context).apply {
            hint = "room name"
        }

        // Create the AlertDialog
        val dialog = AlertDialog.Builder(context).apply {
            setTitle("Enter Room Name")
            setView(input)
            setPositiveButton("Create") { a, _ ->
                val roomName = input.text.toString()
                if (!TextUtils.isEmpty(roomName)) {
                    listener.onCreateRoomName(roomName) // Use the listener callback here
                    a.dismiss()
                } else {
                    Toast.makeText(context, "Room name cannot be empty.", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        }.create()

        dialog.show()
    }

    interface RoomNameDialogListener {
        fun onCreateRoomName(roomName: String)
    }
}
package com.example.studybuddy.view.videocall.webrtc

import org.webrtc.MediaStream

interface LocalStreamListener {
    fun onLocalStreamReady(mediaStream: MediaStream)
}
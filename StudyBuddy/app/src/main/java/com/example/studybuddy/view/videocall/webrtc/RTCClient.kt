package com.example.studybuddy.view.videocall.webrtc

import org.webrtc.IceCandidate
import org.webrtc.PeerConnection
import org.webrtc.SessionDescription

interface RTCClient {

    val peerConnection:PeerConnection

    fun onDestroy()
    fun call()
    fun answer()
    fun onRemoteSessionReceived(sessionDescription: SessionDescription)
    fun addIceCandidateToPeer(iceCandidate: IceCandidate)
    fun sendIceCandidateToPeer(candidate: IceCandidate, target: String)
}
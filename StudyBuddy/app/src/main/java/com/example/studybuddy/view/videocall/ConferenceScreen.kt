package com.example.studybuddy.view.videocall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studybuddy.StudyBuddyApp
import com.example.studybuddy.view.components.ConfirmBackDialog
import com.example.studybuddy.view.components.SurfaceViewRendererComposable
import com.example.studybuddy.view.videocall.viewmodel.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination

@Composable
fun ConferenceScreenRoute(
    roomId: String,
    navigator: DestinationsNavigator,
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    ConferenceScreen(roomId, navigator, mainViewModel)
}
@Composable
fun ConferenceScreen(
    roomId: String,
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel
) {
    val streamState = mainViewModel.mediaStreamsState.collectAsState().value ?: hashMapOf()
    val totalNumberOfStreams = 1 + streamState.count { it.key != StudyBuddyApp.username }

    Column(Modifier.fillMaxSize()) {
        Text(text = "Room name = $roomId")

        val streamModifier = Modifier.fillMaxWidth().weight(1f / totalNumberOfStreams)

        SurfaceViewRendererComposable(
            modifier = streamModifier,
            streamName = "Local",
            onSurfaceReady = { mainViewModel.onRoomJoined(roomId!!, it) }
        )

        streamState.forEach { (streamId, mediaStream) ->
            if (streamId != StudyBuddyApp.username) {
                key(streamId) {
                    SurfaceViewRendererComposable(
                        modifier = streamModifier,
                        streamName = streamId,
                        onSurfaceReady = { surfaceView ->
                            mainViewModel.initRemoteSurfaceView(surfaceView)
                            mediaStream.videoTracks.firstOrNull()?.addSink(surfaceView)
                        }
                    )
                }
            }
        }
    }

    ConfirmBackDialog {
        mainViewModel::onLeaveConferenceClicked.invoke()
        navigator.popBackStack()

    }
}
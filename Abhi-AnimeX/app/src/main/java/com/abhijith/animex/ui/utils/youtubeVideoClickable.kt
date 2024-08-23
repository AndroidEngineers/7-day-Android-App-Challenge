package com.abhijith.animex.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun Modifier.youtubeVideoClickable(videoId: String): Modifier {
    val context = LocalContext.current
    return this.clickable { openYouTubeVideo(context, videoId) }
}
package com.abhijith.animex.ui.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun openYouTubeVideo(context: Context, videoId: String) {
    val videoUrl = "https://www.youtube.com/watch?v=$videoId"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
    intent.setPackage("com.google.android.youtube")  // Try to open in YouTube app

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // YouTube app not found, open in browser instead
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        context.startActivity(browserIntent)
    }
}
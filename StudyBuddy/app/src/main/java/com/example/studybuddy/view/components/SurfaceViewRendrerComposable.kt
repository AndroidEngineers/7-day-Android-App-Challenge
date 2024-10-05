package com.example.studybuddy.view.components

import android.graphics.Color
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.webrtc.SurfaceViewRenderer

@Composable
fun SurfaceViewRendererComposable(
    modifier: Modifier,
    streamName: String,
    onSurfaceReady: (SurfaceViewRenderer) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setPadding(10, 10, 10, 10) // Set padding inside the border
                setBackgroundColor(Color.WHITE) // Border color

                val titleView = TextView(context).apply {
                    text = streamName
                    textSize = 16f // Set the text size
                    setTextColor(Color.BLACK) // Set the text color
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                }
                addView(titleView)

                val frameLayout = FrameLayout(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    ).also {
                        it.setMargins(10, 10, 10, 10) // Margin for the border effect
                    }
                    setBackgroundResource(android.R.drawable.dialog_holo_light_frame) // Use a system resource as an example border

                    addView(SurfaceViewRenderer(context).also {
                        onSurfaceReady.invoke(it)
                    })
                }
                addView(frameLayout)
            }
        }
    )
}
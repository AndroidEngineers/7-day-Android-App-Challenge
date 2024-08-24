package com.abhijith.animex.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abhijith.animex.R
import com.abhijith.animex.ui.theme.Black
import com.abhijith.animex.ui.theme.OrangeBrown
import com.abhijith.animex.ui.utils.openYouTubeVideo

@Composable
fun WatchTrailerButton(text: String, youtubeId: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            openYouTubeVideo(context, youtubeId)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = OrangeBrown,
            contentColor = Black
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
        )
    }
}
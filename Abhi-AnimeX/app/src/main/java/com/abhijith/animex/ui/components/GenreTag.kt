package com.abhijith.animex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abhijith.animex.R

@Composable
fun GenreTag(tag: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color = Color.Gray)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = tag, style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.montserrat_regular)
            )
        )
    }
}
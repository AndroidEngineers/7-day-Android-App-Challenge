package com.abhijith.animex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.abhijith.animex.R

@Composable
fun StatItem(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDesc: String = "",
    count: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDesc,
            tint = Color(0xFFF9A825),
            modifier = modifier
        )
        Text(
            text = count,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = FontFamily(
                Font(R.font.montserrat_regular)
            )
        )
    }
}
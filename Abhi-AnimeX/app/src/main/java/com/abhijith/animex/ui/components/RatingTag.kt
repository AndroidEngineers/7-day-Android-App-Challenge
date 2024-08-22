package com.abhijith.animex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abhijith.animex.R
import com.abhijith.animex.ui.theme.OrangeBrown

@Composable
fun RatingTag(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rating,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color.Black,
            fontFamily = FontFamily(
                Font(R.font.montserrat_regular)
            ),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 4.dp)
        )
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating",
            tint = OrangeBrown
        )
    }
}
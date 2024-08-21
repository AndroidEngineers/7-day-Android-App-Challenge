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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abhijith.animex.R

@Composable
fun FavoriteButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF9A825),
            contentColor = Color.Black
        ),
       /* elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),*/
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
        )
    }
}
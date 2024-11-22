package com.example.studybuddy.view.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studybuddy.ui.theme.inversePrimaryDark
import com.example.studybuddy.ui.theme.onPrimaryContainerLight
import com.example.studybuddy.ui.theme.primaryLight

@Composable
fun CustomToggleButton(
    checked: Boolean,
    onCheckedClick: (Boolean) -> Unit
) {
    val toggleAlignment by animateDpAsState(
        targetValue = if (checked) 32.dp else 0.dp,
        animationSpec = tween(durationMillis = 200)
    )
    // Accessing theme colors
    val colorScheme = MaterialTheme.colorScheme
    val toggleBackgroundColor = if (checked) colorScheme.primary else colorScheme.onSurface.copy(alpha = 0.5f)
    val cardBackgroundColor = colorScheme.background
    Card(
        modifier = Modifier
            .width(60.dp)
            .clickable { onCheckedClick(!checked) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .background(toggleBackgroundColor)
                .fillMaxWidth()
                .padding(4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            CheckCard(
                Modifier
                    .padding(start = toggleAlignment)
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun CheckCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(24.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = CircleShape
    ) {
        Box(modifier = Modifier.background(color = Color.White))
    }
}



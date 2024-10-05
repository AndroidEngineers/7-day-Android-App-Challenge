package com.example.studybuddy.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskCheckBox(
    isCompleted: Boolean,
    borderColor: Color,
    onCheckBoxClicked: () -> Unit,
)
{
    Box (
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .border(2.dp, borderColor, CircleShape)
            .clickable { onCheckBoxClicked() },
        contentAlignment = Alignment.Center
    ){
        AnimatedVisibility(visible = isCompleted) {
            Icon(modifier = Modifier.size(20.dp),
                imageVector = Icons.Rounded.Check,
                contentDescription =null )
        }
    }
}
package com.example.taskninja20.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskninja20.model.CategoryItem

@Composable
fun CategoryGridScreen() {

    // Data for items
    val categories = listOf(
        CategoryItem("In-Progress", Icons.Filled.PlayArrow, 5, Color(0xFF2196F3)), // Blue
        CategoryItem("Pending", Icons.Filled.Settings, 3, Color(0xFFFF9800)), // Orange
        CategoryItem("Completed", Icons.Filled.CheckCircle, 8, Color(0xFF4CAF50)), // Green
        CategoryItem("Cancelled", Icons.Filled.Close, 2, Color(0xFFF44336)) // Red
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier.height(450.dp),
    ) {
        items(categories.size) { index ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = categories[index].color, MaterialTheme.shapes.medium)
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = categories[index].icon,
                        contentDescription = categories[index].name,
                        modifier = Modifier.size(50.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextSemiBold(
                        text = categories[index].name,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomTextRegular(
                        text = "${categories[index].taskCount} Tasks",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryGridScreenPreview() {
    CategoryGridScreen()
}

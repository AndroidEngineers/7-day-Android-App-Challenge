package com.example.taskninja20.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryItem(val name: String,
                        val icon: ImageVector,
                        val taskCount: Int,
                        val color: Color
)


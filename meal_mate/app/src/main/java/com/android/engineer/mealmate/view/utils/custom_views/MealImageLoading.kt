package com.android.engineer.mealmate.view.utils.custom_views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.engineer.mealmate.R

/**
 * This function is used to load images using Coil library.
 */
@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun MealImageLoading(
    imageUrl: String,
    contentDesc: String? = null,
    imageSize: Dp = 150.dp,
    placeholderImage: Int = R.drawable.placeholder_image,
    errorImage: Int = R.drawable.error_image
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDesc,
        placeholder = painterResource(id = placeholderImage),
        error = painterResource(id = errorImage),
        modifier = Modifier.size(imageSize)
    )
}

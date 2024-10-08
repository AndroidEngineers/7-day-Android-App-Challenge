package com.android.engineer.mealmate.view.utils.custom_views

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * This function is used to display the Lottie animation and will be utilized for future purposes.
 */
@Composable
fun MealLottieAnimation(rawResId: Int, imageSize: Dp) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            resId = rawResId
        )
    )

    LottieAnimation(
        modifier = Modifier.size(imageSize - 50.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}
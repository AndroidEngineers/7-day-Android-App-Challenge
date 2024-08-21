package com.abhijith.animex.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.abhijith.animex.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val composition =
            rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animex_splash))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition.value)
        LottieAnimation(
            composition = composition.value,
            progress = logoAnimationState.progress
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            //navController.navigate(Screen.Home.route)
        }
    }
}
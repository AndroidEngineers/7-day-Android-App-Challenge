package com.example.studybuddy.view.splash

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.studybuddy.R
import com.example.studybuddy.view.destinations.LoginScreenRouteDestination
import com.example.studybuddy.view.destinations.MainScreenRouteDestination
import com.example.studybuddy.view.destinations.SplashScreenDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.delay

@Destination(
    start = true,
    deepLinks = [
        DeepLink(
            action = Intent.ACTION_VIEW,
            uriPattern = "study_buddy://splash")
    ]
)

@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    LaunchedEffect(Unit) {
        delay(2000) // Optional delay to show splash screen

        val isLoggedIn = FirebaseAuth.getInstance().currentUser != null

        if (isLoggedIn) {
            navigator.navigate(MainScreenRouteDestination(0)) {
                popUpTo(SplashScreenDestination) { inclusive = true }
            }
        } else {
            navigator.navigate(LoginScreenRouteDestination) {
                popUpTo(SplashScreenDestination) { inclusive = true }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        SplashScreenDesign()
    }
}



@Composable
fun SplashScreenDesign() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.splash_screen), // Replace with your logo resource
            contentDescription = "App Logo",
            modifier = Modifier.fillMaxSize(), // Adjust size as needed
            contentScale = ContentScale.Crop
        )
    }
}

// TODO: implement some sort of animation to the splash screen



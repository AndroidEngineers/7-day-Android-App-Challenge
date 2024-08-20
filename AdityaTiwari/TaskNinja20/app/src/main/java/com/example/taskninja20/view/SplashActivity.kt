package com.example.taskninja20.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskninja20.R
import com.example.taskninja20.reusable.CustomTextBold
import com.example.taskninja20.ui.theme.RobotoFontFamily
import com.example.taskninja20.ui.theme.TaskNinja20Theme

import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskNinja20Theme {
                SplashScreen()
            }
        }
    }
}

@Composable
fun SplashScreen() {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(1000)
        alpha.animateTo(1f, animationSpec = tween(1500))
    }
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo), 
                contentDescription = "Task Ninja Logo",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .alpha(alpha.value)
            )


            Spacer(modifier = Modifier.height(16.dp))


            CustomTextBold(
                text = "Task Ninja 2.0",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.alpha(alpha.value),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TaskNinja20Theme {
        SplashScreen()
    }
}
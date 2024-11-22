package com.learning.composelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.composelearning.ui.theme.ComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLearningTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpeCalculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SimpeCalculator(modifier: Modifier) {

    val userInput = remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
                .padding(10.dp),
            text = userInput.value,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
        )


        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 7
                }
            ) {

                Text(text = "7")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 8
                }
            ) {
                Text(text = "8")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 9
                }
            ) {
                Text(text = "9")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 4
                }
            ) {
                Text(text = "4")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 5
                }
            ) {
                Text(text = "5")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 6
                }
            ) {
                Text(text = "6")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 1
                }
            ) {
                Text(text = "1")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 2
                }
            ) {
                Text(text = "2")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    userInput.value += 3
                }
            ) {
                Text(text = "3")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.Green),
                onClick = {
                    userInput.value += "+"
                }
            ) {

                Text(text = "+")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = {
                    userInput.value += "-"

                }
            ) {
                Text(text = "-")
            }

            Button(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    if (userInput.value.contains("+")) {
                        val pars = userInput.value.split("+")
                        val num1 = pars[0]
                        val num2 = pars[1]
                        val sum = num1.toInt() + num2.toInt()
                        userInput.value = sum.toString()
                    } else if (userInput.value.contains("-")) {
                        val pars = userInput.value.split("-")
                        val num1 = pars[0]
                        val num2 = pars[1]
                        val sub = num2.toInt() - num1.toInt()
                        userInput.value = sub.toString()
                    }
                }
            ) {
                Text(text = "=")
            }
        }
    }

}
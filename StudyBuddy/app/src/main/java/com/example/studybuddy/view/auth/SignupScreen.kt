package com.example.studybuddy.view.auth

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studybuddy.R
import com.example.studybuddy.ui.theme.BlueText
import com.example.studybuddy.ui.theme.buttonColor
import com.example.studybuddy.ui.theme.grey
import com.example.studybuddy.view.destinations.LoginScreenRouteDestination
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@com.ramcosta.composedestinations.annotation.Destination(
    deepLinks = [
        DeepLink(action = Intent.ACTION_VIEW,
            uriPattern = "study_buddy://signup")
    ]
)
@Composable
fun SignupScreenRoute(
    navigator: DestinationsNavigator,
) {
    val viewModel: AuthViewModel = hiltViewModel()
    SignupScreen(
        viewModel = viewModel,
        onLoginClick = { navigator.navigateUp() },
        onSignupSuccess = { navigator.navigate(LoginScreenRouteDestination)  }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: AuthViewModel,
    onLoginClick: () -> Unit,
    onSignupSuccess: () -> Unit
) {
    var userName by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    val authState by viewModel.authState.observeAsState()
    val context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Loading -> {
                // Show loading indicator
            }
            is AuthState.Error -> {
                val message = (authState as AuthState.Error).message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
            is AuthState.Authenticated -> {
                onSignupSuccess()
            }
            else -> Unit
        }
    }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.signup),
                    contentDescription = "Signup Image",
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .size(250.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sign up",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(modifier = Modifier.padding(horizontal = 25.dp)) {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = grey,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        placeholder = {
                            Text("Enter your Name")
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Mobile Number",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = userPhone,
                        onValueChange = {
                            userPhone = it},
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = grey,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        placeholder = {
                            Text("Enter your Phone Number")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = userEmail,
                        onValueChange = { userEmail = it },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = grey,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        placeholder = {
                            Text("Enter your Email")
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = grey,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        placeholder = {
                            Text("Enter your password")
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordVisible)
                                painterResource(id = R.drawable.baseline_visibility_24)
                            else
                                painterResource(id = R.drawable.baseline_visibility_off_24)

                            IconButton(onClick = {
                                passwordVisible = !passwordVisible
                            }) {
                                Image(
                                    painter = icon,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Confirm Password",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = grey,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        placeholder = {
                            Text("Re-enter your password")
                        },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (confirmPasswordVisible)
                                painterResource(id = R.drawable.baseline_visibility_24)
                            else
                                painterResource(id = R.drawable.baseline_visibility_off_24)

                            IconButton(onClick = {
                                confirmPasswordVisible = !confirmPasswordVisible
                            }) {
                                Image(
                                    painter = icon,
                                    contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                                )
                            }
                        }
                    )


                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            if (userEmail.isNotBlank() && isValidEmail(userEmail) &&
                                password.isNotBlank() && confirmPassword.isNotBlank() &&
                                password == confirmPassword && userName.isNotBlank() && userPhone.isNotBlank()
                            ) {
                                viewModel.signUp(
                                    name = userName,
                                    number = userPhone,
                                    premiumMember = false,
                                    expiry = 0,
                                    email = userEmail,
                                    password = password
                                )
                                userName = ""
                                userEmail = ""
                                password = ""
                                confirmPassword = ""
                                userPhone = ""
                            } else {
                                if (password != confirmPassword) {
                                    viewModel._authState.value = AuthState.Error("Passwords do not match")
                                } else if (!isValidEmail(userEmail)) {
                                    viewModel._authState.value = AuthState.Error("Please fill a valid email")
                                } else {
                                    viewModel._authState.value = AuthState.Error("Please fill all fields correctly")
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp, vertical = 20.dp),
                        colors = ButtonDefaults.buttonColors(buttonColor),
                        enabled = viewModel._authState.value != AuthState.Loading
                    ) {
                        Text(text = "Sign up")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = "Have an account?",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Log in",
                            style = MaterialTheme.typography.bodyMedium.copy(color = BlueText),
                            modifier = Modifier.clickable { onLoginClick() }
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

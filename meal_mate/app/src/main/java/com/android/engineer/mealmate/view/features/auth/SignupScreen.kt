package com.android.engineer.mealmate.view.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.utils.constants.DASHBOARD
import com.android.engineer.mealmate.view.utils.constants.sealed_constants.AuthScreen
import com.android.engineer.mealmate.view.utils.custom_views.MealFilledButton
import com.android.engineer.mealmate.view.utils.custom_views.MealText
import com.android.engineer.mealmate.view.utils.custom_views.MealTextField

@Composable
fun SignupScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        content = { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .background(OrangeOnPrimary),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        MealText(
                            text = stringResource(id = R.string.lets_get_started),
                            modifier = Modifier,
                            28.sp,
                            textAlign = TextAlign.Center
                        )
                        MealText(
                            text = stringResource(id = R.string.create_an_account),
                            modifier = Modifier,
                            12.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        MealTextField(
                            value = "",
                            onValueChange = {},
                            label = "Username",
                            icon = Icons.Filled.Person
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        MealTextField(
                            value = "",
                            onValueChange = {},
                            label = "First Name",
                            icon = Icons.Filled.PersonOutline
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        MealTextField(
                            value = "",
                            onValueChange = {},
                            label = "Last Name",
                            icon = Icons.Filled.PersonOutline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        MealTextField(
                            value = "",
                            onValueChange = {},
                            label = "Email",
                            icon = Icons.Filled.Email
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        MealFilledButton(
                            onClick = { navHostController.navigate(route = DASHBOARD) },
                            text = stringResource(id = R.string.sign_up),
                            modifier = Modifier.fillMaxWidth(),
                            horizontalPadding = 8.dp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)){
                            MealText(
                                text = stringResource(id = R.string.already_have_an_account),
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            MealText(
                                text = stringResource(id = R.string.sign_in_lower),
                                fontSize = 12.sp,
                                modifier = Modifier.clickable { navHostController.navigate(AuthScreen.Login.route) }
                            )

                        }

                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SignupScreenPreview() {
    SignupScreen(navHostController = rememberNavController())
}
package com.android.engineer.mealmate.view.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangeOnBackground
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.ui.theme.OrangePrimary
import com.android.engineer.mealmate.view.utils.constants.nav.BottomBarScreen
import com.android.engineer.mealmate.view.utils.custom_views.MealProfileImage
import com.android.engineer.mealmate.view.utils.custom_views.MealText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navHostController: NavHostController, logout: () -> Unit) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val userName = viewModel.userName.collectAsState()
    val email = viewModel.email.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = OrangeOnPrimary,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    scrolledContainerColor = OrangeOnBackground
                ),
                title = {
                    Text(text = BottomBarScreen.Profile.title)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OrangeOnPrimary)
                .padding(paddingValues = paddingValues)
                .padding(all = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MealProfileImage(R.drawable.ic_person)
            MealText(text = userName.value)
            MealText(text = email.value)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 28.dp))
            Spacer(modifier = Modifier.height(30.dp))
            FilledIconButton(
                modifier = Modifier.fillMaxWidth(),
                colors = IconButtonColors(
                    containerColor = OrangePrimary,
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                onClick = { }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                    ) {
                    Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.ic_protein), contentDescription = null)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = stringResource(id = R.string.food_preferences))
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            FilledIconButton(
                modifier = Modifier.fillMaxWidth(),
                colors = IconButtonColors(
                    containerColor = OrangePrimary,
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                onClick = { }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(modifier = Modifier.size(24.dp), imageVector = Icons.Default.Share, contentDescription = null)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = stringResource(id = R.string.share_app))
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            FilledIconButton(
                modifier = Modifier.fillMaxWidth(),
                colors = IconButtonColors(
                    containerColor = OrangePrimary,
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                onClick = {
                    viewModel.onLogoutClicked {
                        /*navHostController.popBackStack()
                        navHostController.navigate(ROOT)
                        logout()*/
                    }
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.ic_logout), contentDescription = null)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = stringResource(id = R.string.logout))
                }
            }
        }
    }
}

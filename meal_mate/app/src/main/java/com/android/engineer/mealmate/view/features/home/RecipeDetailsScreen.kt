package com.android.engineer.mealmate.view.features.home

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.engineer.mealmate.R
import com.android.engineer.mealmate.ui.theme.OrangeOnBackground
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary
import com.android.engineer.mealmate.view.utils.custom_views.MealLottieAnimation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(recipeId: Int, navHostController: NavHostController) {

    val viewModel = hiltViewModel<RecipeInfoByIdViewModel>()
    if(viewModel.isWebViewLoading.value) {
        viewModel.getInformationById(recipeId = recipeId)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            MealLottieAnimation(rawResId = R.raw.loading_animation, imageSize = 200.dp)
        }
    } else {
        Scaffold(
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
                        Text(text = viewModel.recipeTitle.value, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                )
            }
        ) { paddingValues ->
            // Adding a WebView inside AndroidView with layout as full screen
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        webViewClient = WebViewClient()
                        loadUrl(viewModel.webViewUrl.value)
                    }
                }, update = {
                    it.loadUrl(viewModel.webViewUrl.value)
                })
        }
    }
}

package com.android.engineer.mealmate.view.features.home

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.engineer.mealmate.data.utils.STATIC_URL1
import com.android.engineer.mealmate.ui.theme.OrangeOnBackground
import com.android.engineer.mealmate.ui.theme.OrangeOnPrimary

// recipe Id will be require when integrating the API.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(recipeTitle: String, recipeSourceUrl: String, navHostController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                },
                colors = TopAppBarColors(
                    containerColor = OrangeOnPrimary,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    scrolledContainerColor = OrangeOnBackground
                ),
                title = { Text(text = recipeTitle, maxLines = 2, overflow = TextOverflow.Ellipsis) }
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
                loadUrl(recipeSourceUrl)
            }
        }, update = {
            it.loadUrl(recipeSourceUrl)
        })
    }
}

package com.example.reciperoulette.presentation.ui.commonui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.reciperoulette.R


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HorizontalListImage(ingredientImage: String){
    GlideImage(
        model = ingredientImage,
        contentDescription = stringResource(id = R.string.app_name),
        modifier = Modifier.size(88.dp),
        contentScale = ContentScale.FillBounds,
        failure = placeholder(R.drawable.ic_launcher_foreground)
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeImage(image: String) {
    GlideImage(
        model = image,
        contentDescription = stringResource(id = R.string.app_name),
        modifier = Modifier.fillMaxWidth().height(250.dp),
        contentScale = ContentScale.Fit
    )
}
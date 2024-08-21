package com.example.make_food.ui.commonui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reciperoulette.R
import com.example.reciperoulette.presentation.recipelist.ui.theme.RecipeRouletteTheme


@Composable
fun HorizontalListImage(ingredientImage: Int = R.drawable.ic_launcher_background){
    Image(
        painterResource(id = ingredientImage),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(88.dp)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun HorizontalListPreview() {
    RecipeRouletteTheme {
        HorizontalListImage(R.drawable.ic_launcher_foreground)
    }
}



@Composable
fun RecipeImage(){
    Image(
        painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun RecipeImagePreview() {
    RecipeRouletteTheme {
        RecipeImage()
    }
}
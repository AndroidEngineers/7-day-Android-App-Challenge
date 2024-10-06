package com.example.jay_recipesx.features.RecipeHome.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.jay_recipesx.Core.AppFunctions.AppFunctions.Companion.removeHtmlTags
import com.example.jay_recipesx.Core.Components.ErrorComponent
import com.example.jay_recipesx.Core.Components.LoadingComponent
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeErrorState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeLoadingState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeSuccessState

@Composable
fun RecipeHomeScreen(modifier: Modifier = Modifier, state: State<RecipeHomeState>) {

    if(state.value is RecipeHomeLoadingState){
        LoadingComponent()
    }
    else if(state.value is RecipeHomeErrorState) {
        ErrorComponent(errorMessage = (state.value as RecipeHomeErrorState).errorMessage)
    }
    else if(state.value is RecipeHomeSuccessState){
        val recipe = (state.value as RecipeHomeSuccessState).recipes
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val twentyPercentHeight = screenHeight * 0.1f
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = twentyPercentHeight,
                    start = 28.dp,
                    end = 28.dp
                ),
        ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)), // Clip the image directly
                        model = recipe.image,
                        contentDescription = null,
                        loading = {
                            Box {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        },
                    )
                }

                // Add Spacer to push the text down
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = recipe.title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(28.dp))
                ContentComponent(
                    heading = "Summary",
                    content = recipe.summary,
                    maxLine = 4
                )

                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // Share action
                        }
                    ) {
                        Text(text = "Share")
                    }

                    Button(
                        onClick = {
                            // View more details action
                        }
                    ) {
                        Text(text = "View More Details")
                    }
                }

                // Uncomment if you want to show instructions
                // Spacer(modifier = Modifier.height(28.dp))
                // ContentComponent(heading = "Instructions", content = addBulletsToInstructions(recipe.instructions))
        }
    }
    else {
        ErrorComponent(errorMessage = "Something went wrong")
    }

}

@Composable
fun ContentComponent(
    modifier: Modifier = Modifier,
    heading: String,
    content: String,
    maxLine: Int = Int.MAX_VALUE
) {

    Column {
        Text(text = heading,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            removeHtmlTags(content),
            textAlign = TextAlign.Justify,
            maxLines = maxLine,
            overflow = TextOverflow.Ellipsis
        )
    }
}




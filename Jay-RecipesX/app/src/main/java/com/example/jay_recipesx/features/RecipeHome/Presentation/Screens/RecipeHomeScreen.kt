package com.example.jay_recipesx.features.RecipeHome.Presentation.Screens

import ContentComponent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.jay_recipesx.Core.AppFunctions.AppFunctions.Companion.addBulletsToInstructions
import com.example.jay_recipesx.Core.AppFunctions.AppFunctions.Companion.removeHtmlTags
import com.example.jay_recipesx.Core.Components.ErrorComponent
import com.example.jay_recipesx.Core.Components.LoadingComponent
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeErrorState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeEvent
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeLoadingState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeSuccessState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeViewModel

@Composable
fun RecipeHomeScreen(modifier: Modifier = Modifier, viewModel: RecipeViewModel) {


    val state = viewModel.recipes.collectAsState()

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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = twentyPercentHeight,
                    start = 28.dp,
                    end = 28.dp
                ),
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)) // Ensure the image itself has rounded corners
                            .fillMaxSize(), // Ensure the image fills the entire Box
                        model = recipe.image,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds, // Fill all available space in the Box
                        loading = {
                            Box {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        },
                    )
                }

                // Add Spacer to push the text down
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = recipe.title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    lineHeight = 32.sp,
                )
                Spacer(modifier = Modifier.height(28.dp))
                                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            viewModel.add(RecipeHomeEvent.GetRandomRecipeEvent())
                        }
                    ) {
                        Text(text = "See Next")
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
                ContentComponent(
                    heading = "Summary",
                    content = recipe.summary,
                )

                Spacer(modifier = Modifier.height(28.dp))
                ContentComponent(
                    heading = "Instructions",
                    content = addBulletsToInstructions(instructions = recipe.instructions)
                )
                Spacer(modifier = Modifier.height(28.dp))


                // Don't show if steps are not available ot analyzedInstructions is empty
                if(recipe.analyzedInstructions.isNotEmpty())
                Column {

                    Text(text = "Steps",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    recipe.analyzedInstructions.get(0).steps.forEach() { step ->

                        val stepNumber = step.number.toString()
                        val stepDescription = step.step
                        Row(
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {
                            Text(text = "$stepNumber. ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = stepDescription,
                                textAlign = TextAlign.Justify,
                                overflow = TextOverflow.Ellipsis

                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
    else {
        ErrorComponent(errorMessage = "Something went wrong")
    }

}
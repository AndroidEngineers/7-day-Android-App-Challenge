package com.example.jay_recipesx.features.RecipeHome.Presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeErrorState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeLoadingState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeState
import com.example.jay_recipesx.features.RecipeHome.Presentation.ViewModel.RecipeHomeSuccessState

@Composable
fun RecipeHomeScreen(modifier: Modifier = Modifier, state: State<RecipeHomeState>) {

    if(state.value is RecipeHomeLoadingState){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    }
    else if(state.value is RecipeHomeErrorState) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = (state.value as RecipeHomeErrorState).errorMessage)
        }
    }

    else if(state.value is RecipeHomeSuccessState){

        Column  {


            AsyncImage(
                model = (state.value as RecipeHomeSuccessState).recipes.imageUrl,
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
            Text(text = (state.value as RecipeHomeSuccessState).recipes.name)

        }
    }

    else {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Something went wrong")
        }
    }
}
package com.example.anifetch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

//class AnimeDetailScreen(animeId: String, navController: NavHostController) {
//
//}
@Composable
fun AnimeDetailScreen(
    animeId: Int, navController:NavHostController){
    //to hold anime details
    var animeDetails by remember { mutableStateOf<AnimeDetailsResponse?>(null)}
    var characters by remember { mutableStateOf<List<Character>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true)}
    val scope = rememberCoroutineScope()
    //fetch anime Details when composable is loaded
    LaunchedEffect(animeId) {
        scope.launch {
            try {
                val detailsResponse = RetrofitInstance.api.getAnimeDetails(animeId)
                val charactersResponse = RetrofitInstance.api.getAnimeCharacters(animeId)
                animeDetails = detailsResponse
                characters = charactersResponse.data
            } catch (e:Exception){
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
    //Display loading spinner while data is being fetch
    if(isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else{
        //Display anime details
        animeDetails?.let{details ->
            Column(modifier = Modifier.padding(16.dp)) {
                //BackBtn
                Row (modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically){
                    IconButton(onClick = { /*navigateBack*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = details.title?: "Unknown Title",
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                //large thumbnail
                Image(painter = rememberAsyncImagePainter(details.images.jpg.image_url),
                    contentDescription = details.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.height(16.dp))
                //Anime detail
                Text(text = "Title:${details.title}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Description: ${details.synopsis.orEmpty()}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                //Main Characters
                //Stillwork
            }
        }
    }
}

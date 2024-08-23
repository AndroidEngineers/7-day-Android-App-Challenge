package com.example.anifetch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.anifetch.R
import com.example.anifetch.ui.components.LoadingSpinner
import com.example.anifetch.utils.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(navController: NavController, animeId: Int){
    val animeDetails = remember { mutableStateOf(MockData.getMockAnimeResponse().data.firstOrNull{it.mal_id == animeId}) }
    val characters = remember { mutableStateOf(MockData.getMockCharacterResponse().data) }
    var isLoading by remember { mutableStateOf(animeDetails.value == null) }
    
    LaunchedEffect(animeId) {
        animeDetails.value = MockData.getMockAnimeResponse().data.firstOrNull { it.mal_id == animeId }
        characters.value = MockData.getMockCharacterResponse().data
        isLoading = false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(animeDetails.value?.title.orEmpty()) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack , contentDescription = "Back")
                    }
                }
            )
        },
        content = {padding ->
            if(isLoading){
                LoadingSpinner()
            }else{
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)){
                    animeDetails.value?.let { anime->
                        Image(painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                            contentDescription = anime.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Crop)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(R.string.title, anime.title), style = MaterialTheme.typography.displayLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(stringResource(R.string.description, anime.synopsis.orEmpty()), style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(R.string.rating_8_5_10), style = MaterialTheme.typography.headlineMedium)
                        Text(stringResource(R.string.genres_action_drama), style = MaterialTheme.typography.headlineMedium)
                        Text(stringResource(R.string.episodes_24), style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(R.string.main_characters), style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        characters.value.forEach{character ->
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                            ){Image(
                                painter = rememberAsyncImagePainter(character.images.jpg.image_url),
                                contentDescription = character.name,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 8.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column {
                                Text(character.name, style= MaterialTheme.typography.headlineMedium)
                                Text(character.role, style= MaterialTheme.typography.bodyLarge)
                             }
                            }
                        }
                    }
                }
            }
        }
    )
}
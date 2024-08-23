package com.example.anifetch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.anifetch.models.AnimeItem

@Composable
fun AnimeItemView(anime: AnimeItem, onClick: ()-> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() })
    {
        Image(painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
            contentDescription =anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = anime.title,
            style = MaterialTheme.typography.displayLarge,
            maxLines = 1
        )
        Text(
            text = anime.synopsis.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2
        )
    }
}

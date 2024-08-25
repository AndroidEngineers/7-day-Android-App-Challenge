package com.example.anifetch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.anifetch.R
import com.example.anifetch.ui.components.SearchBar
import com.example.anifetch.models.AnimeItem
import com.example.anifetch.ui.components.AnimeItemView
import com.example.anifetch.ui.components.LoadingSpinner
import com.example.anifetch.ui.theme.AnifecthTheme
import com.example.anifetch.utils.MockData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val animeList = remember { mutableStateListOf<AnimeItem>() }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
       animeList.addAll(MockData.getMockAnimeResponse().data)
        isLoading = false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { stringResource(R.string.anifetch) },
                actions ={
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            )
        },
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {

                if (isLoading){
                    LoadingSpinner()
                }else{
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(animeList){anime ->
                            AnimeItemView(anime = anime, onClick = {
                               navController.navigate("details/${anime.mal_id}")
                            })
                        }
                    }
                }
            }
        }
    )
}

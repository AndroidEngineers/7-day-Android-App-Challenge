package com.example.anifetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.anifetch.ui.theme.AnifecthTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnifecthTheme {
                //navigate to animedetailScreen
                val navController = rememberNavController()
                NavHost(navController =navController, startDestination = "main") {
                    composable("main"){ MainScreen(navController)}
                    composable("animeDetail/{animeId}"){backStackEntry->
                        val animeId = backStackEntry.arguments?.getString("animeId")?.toInt() ?: return@composable
                        AnimeDetailScreen(animeId,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val animeList = remember { mutableStateListOf<AnimeItems>() } // Replace with real data source
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitInstance.api.getAnimeList(page = 1, limit = 20)
                animeList.addAll(response.data)
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                isLoading = false
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()){
        //SearchBar
        SearchBar(
            query = searchQuery,
            onQueryChange = {searchQuery=it},
            modifier = Modifier.padding(16.dp)
        )
        //AnimeList
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ){
            items(animeList){anime->
                AnimeItemView(anime = anime, onClick = {
                    navController.navigate("animeDetail/${anime.mal_id}")
                })
            }
        }
        if (isLoading){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
    }
}
@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange:(TextFieldValue)->Unit,
    modifier: Modifier=Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp),
       contentAlignment = Alignment.Center
    ) {
        //SearchIcon
        /*Icon(imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterStart)
                .padding(end = 16.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )*/
            //Basic Text Field for search input
        BasicTextField(value = query, onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
//            .height(56.dp)
//            .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .padding(start = 16.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        ){ innerTextField ->
            // Display placeholder text when query is empty
            if (query.text.isEmpty()) {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)),
                    modifier = Modifier.padding(start = 0.dp)
                )
            }
            // Render the actual text field
            innerTextField()
        }
    }
}



@Composable
fun AnimeItemView(anime: AnimeItems, onClick:()->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }//Handle click
    ){
        Image(
            painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
            contentDescription =anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = anime.title,
            style = MaterialTheme.typography.displayLarge,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = anime.synopsis.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2
        )
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewMainScreen() {
//    AnifecthTheme {
//        MainScreen()
//    }
//}
@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    AnifecthTheme {
        SearchBar(query = TextFieldValue(""), onQueryChange = {})
    }
}
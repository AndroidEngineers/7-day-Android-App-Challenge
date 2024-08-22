package com.abhijith.animex.ui.screens.animelist

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.abhijith.animex.ui.screens.Screen
import com.abhijith.animex.ui.screens.animelist.viewmodel.AnimeListViewModel
import com.google.gson.Gson

@Composable
fun AnimeList(navController: NavController, viewModel: AnimeListViewModel = viewModel()) {
    val animeItems by viewModel.items.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { anime ->
            val animeJson = Uri.encode(Gson().toJson(anime))
            navController.navigate(
                Screen.AnimeDetails.route.replace("{selectedAnimeItem}", animeJson))
            // Reset the navigation event
            viewModel.onResetNavigation()
        }
    }

    LazyColumn {
        items(animeItems.count()) {
            AnimeListItem(
                anime = animeItems[it],
                onItemClicked = viewModel::onItemClick,
                onButtonClicked = viewModel::onButtonClick
            )
        }
    }
}
// to bring the click events outside the composable if possible
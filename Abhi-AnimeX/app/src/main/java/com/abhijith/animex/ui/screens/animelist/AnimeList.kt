package com.abhijith.animex.ui.screens.animelist

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.domain.usecases.GetAnimeListUseCase
import com.abhijith.animex.ui.navigation.Screen
import com.abhijith.animex.ui.screens.animelist.viewmodel.AnimeListViewModel
import com.abhijith.animex.ui.screens.animelist.viewmodel.AnimeListViewModelFactory
import com.abhijith.animex.ui.screens.error.ErrorScreen
import com.abhijith.animex.ui.screens.loading.LoadingScreen
import com.google.gson.Gson

@Composable
fun AnimeList(navController: NavController) {

    // TODO to update this once DI is added to the project
    val getAnimeListUseCase = GetAnimeListUseCase()
    val factory = AnimeListViewModelFactory(getAnimeListUseCase)
    val viewModel: AnimeListViewModel = viewModel(factory = factory)

    val navigationEvent by viewModel.navigationEvent.collectAsState()

    // TODO to move this out of this composable
    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { anime ->
            val animeJson = Uri.encode(Gson().toJson(anime))
            navController.navigate(
                Screen.AnimeDetails.route.replace("{selectedAnimeItem}", animeJson)
            )
            // Reset the navigation event
            viewModel.onResetNavigation()
        }
    }

    when (val uiState = viewModel.itemsUiState.collectAsState().value) {
        is AnimeListUiState.Success -> AnimeListInfo(uiState.items, viewModel)
        is AnimeListUiState.Loading -> LoadingScreen()
        is AnimeListUiState.Error -> ErrorScreen(uiState.message)
    }
}

@Composable
fun AnimeListInfo(animeItems: List<AnimeItem>, viewModel: AnimeListViewModel) {
    LazyColumn {
        items(animeItems.count()) {
            AnimeListItem(
                animeEntity = animeItems[it],
                onItemClicked = viewModel::onItemClick
            )
        }
    }
}

// TODO to bring the click events outside the composable if possible
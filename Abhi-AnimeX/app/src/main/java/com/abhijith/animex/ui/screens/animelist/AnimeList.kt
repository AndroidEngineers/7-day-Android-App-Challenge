package com.abhijith.animex.ui.screens.animelist

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.ui.navigation.Screen
import com.abhijith.animex.ui.screens.animelist.viewmodel.AnimeListViewModel
import com.abhijith.animex.ui.screens.error.ErrorScreen
import com.abhijith.animex.ui.screens.loading.LoadingScreen
import com.google.gson.Gson

@Composable
fun AnimeList(navController: NavController, viewModel: AnimeListViewModel = hiltViewModel()) {
    val uiState by viewModel.itemsUiState.collectAsState()
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    // TODO (issue 12)
    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { anime ->
            val animeJson = Uri.encode(Gson().toJson(anime))
            navController.navigate(
                Screen.AnimeDetails.route.replace("{selectedAnimeItem}", animeJson)
            )
            viewModel.onResetNavigation()
        }
    }

    when (uiState) {
        is AnimeListUiState.Success -> AnimeListInfo(
            animeItems = (uiState as AnimeListUiState.Success).items,
            onItemClick = viewModel::onItemClick
        )

        AnimeListUiState.Loading -> LoadingScreen()
        is AnimeListUiState.Error -> ErrorScreen((uiState as AnimeListUiState.Error).message)
    }
}

@Composable
fun AnimeListInfo(animeItems: List<AnimeItem>, onItemClick: (AnimeItem) -> Unit) {
    LazyColumn(modifier = Modifier.testTag("AnimeListInfo")) {
        items(animeItems.count()) { i ->
            AnimeListItem(
                animeEntity = animeItems[i],
                onItemClicked = onItemClick
            )
        }
    }
}
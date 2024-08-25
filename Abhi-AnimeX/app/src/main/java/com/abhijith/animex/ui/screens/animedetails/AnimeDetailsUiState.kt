package com.abhijith.animex.ui.screens.animedetails

import com.abhijith.animex.domain.models.AnimeItem

sealed class AnimeDetailsUiState {
    data object Loading : AnimeDetailsUiState()
    data class Success(val item: AnimeItem) : AnimeDetailsUiState()
    data class Error(val message: String) : AnimeDetailsUiState()
}

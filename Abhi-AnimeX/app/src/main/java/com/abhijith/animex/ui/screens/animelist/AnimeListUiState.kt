package com.abhijith.animex.ui.screens.animelist

import com.abhijith.animex.domain.models.AnimeItem

sealed class AnimeListUiState {
    data object Loading : AnimeListUiState()
    data class Success(val items: List<AnimeItem>) : AnimeListUiState()
    data class Error(val message: String) : AnimeListUiState()
}

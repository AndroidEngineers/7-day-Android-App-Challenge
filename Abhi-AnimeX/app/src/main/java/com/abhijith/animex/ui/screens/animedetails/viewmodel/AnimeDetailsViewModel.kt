package com.abhijith.animex.ui.screens.animedetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.animex.domain.models.AnimeItem
import com.abhijith.animex.ui.screens.animedetails.AnimeDetailsUiState
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _uiState = MutableStateFlow<AnimeDetailsUiState>(AnimeDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = AnimeDetailsUiState.Loading
            try {
                delay(3000)
                val animeItemString = savedStateHandle.get<String>("selectedAnimeItem")
                _uiState.value =
                    AnimeDetailsUiState.Success(
                        Gson().fromJson(
                            animeItemString,
                            AnimeItem::class.java
                        )
                    )
            } catch (e: Exception) {
                _uiState.value =
                    AnimeDetailsUiState.Error("Something went wrong! \nPlease try again later...")
            }
        }
    }
}


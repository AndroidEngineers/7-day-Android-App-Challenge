package com.abhijith.animex.ui.screens.animedetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.animex.data.model.Anime
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _animeItem = MutableStateFlow(Anime())
    val animeItem = _animeItem.asStateFlow()

    init {
        viewModelScope.launch {
            val animeItemString = savedStateHandle.get<String>("selectedAnimeItem")
            _animeItem.value = Gson().fromJson(animeItemString, Anime::class.java)
        }
    }

    fun formatRatingText() {

    }
}


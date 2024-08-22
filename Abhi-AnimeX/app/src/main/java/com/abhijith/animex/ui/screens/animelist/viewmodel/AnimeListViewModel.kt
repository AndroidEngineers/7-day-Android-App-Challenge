package com.abhijith.animex.ui.screens.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.animex.data.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Anime>>(emptyList())
    val items: StateFlow<List<Anime>> = _items.asStateFlow()

    init {
        viewModelScope.launch {
            _items.value = List(10) { generateValidAnime() }
        }
    }

    private fun generateValidAnime(): Anime {
        val anime = Anime()

        val cleanedTitle = anime.title.replace("\\", "").replace("\"", "")
        val cleanedYear = anime.year.ifBlank {
            "-"
        }
        val cleanedRating = anime.rating.ifBlank {
            "-"
        }
        val cleanedRank = anime.rank.ifBlank {
            "-"
        }
        val cleanedScore = anime.score.ifBlank {
            "-"
        }

        return anime.copy(
            title = cleanedTitle,
            year = cleanedYear,
            rating = cleanedRating,
            rank = cleanedRank,
            score = cleanedScore,
        )
    }

    fun onItemClick(item: Anime) {
        Log.d("AnimeListViewModel", "Clicked on item: $item.title")
    }

    fun onButtonClick(item: String) {
        Log.d("AnimeListViewModel", "Youtube button clicked on item: $item")
    }
}
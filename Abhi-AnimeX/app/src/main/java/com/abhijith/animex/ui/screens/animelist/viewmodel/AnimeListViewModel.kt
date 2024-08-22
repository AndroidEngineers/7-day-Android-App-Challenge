package com.abhijith.animex.ui.screens.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items.asStateFlow()

    init {
        viewModelScope.launch {
            _items.value = List(15) { "$it" }
        }
    }

    fun onItemClick(item: String) {
        Log.d("AnimeListViewModel", "Clicked on item: $item")
    }

    fun onButtonClick(item: String) {
        Log.d("AnimeListViewModel", "Youtube button clicked on item: $item")
    }
}
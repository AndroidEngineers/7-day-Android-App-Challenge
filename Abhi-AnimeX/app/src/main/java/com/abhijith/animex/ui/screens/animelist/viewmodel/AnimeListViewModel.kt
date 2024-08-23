package com.abhijith.animex.ui.screens.animelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.abhijith.animex.domain.model.AnimeItem
import com.abhijith.animex.domain.usecases.GetAnimeListUseCase
import com.abhijith.animex.ui.screens.animelist.AnimeListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel(private val getAnimeListUseCase: GetAnimeListUseCase) : ViewModel() {
    private val _itemsUiState = MutableStateFlow<AnimeListUiState>(AnimeListUiState.Loading)
    val itemsUiState: StateFlow<AnimeListUiState> = _itemsUiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<AnimeItem?>(null)
    val navigationEvent: StateFlow<AnimeItem?> = _navigationEvent.asStateFlow()

    init {
        viewModelScope.launch {
            _itemsUiState.value = AnimeListUiState.Loading
            delay(3000)
            try {
                _itemsUiState.value = AnimeListUiState.Success(getAnimeListUseCase())
            } catch (e: Exception) {
                _itemsUiState.value =
                    AnimeListUiState.Error("Something went wrong! \nPlease try again later...")
            }
        }
    }

    fun onItemClick(item: AnimeItem) {
        _navigationEvent.value = item
    }

    fun onResetNavigation() {
        _navigationEvent.value = null
    }
}


// TODO to remove this once i add DI to the project
class AnimeListViewModelFactory(private val getAnimeListUseCase: GetAnimeListUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeListViewModel(getAnimeListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
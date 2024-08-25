package com.abhijith.animex.ui.screens.animelist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.animex.domain.models.AnimeItem
import com.abhijith.animex.domain.usecases.GetSeasonalAnimeListUseCase
import com.abhijith.animex.ui.screens.animelist.AnimeListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val seasonalAnimeListUseCase: GetSeasonalAnimeListUseCase
) :
    ViewModel() {

    private val tag = AnimeListViewModel::class.java.simpleName

    private val _itemsUiState = MutableStateFlow<AnimeListUiState>(AnimeListUiState.Loading)
    val itemsUiState: StateFlow<AnimeListUiState> = _itemsUiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<AnimeItem?>(null)
    val navigationEvent: StateFlow<AnimeItem?> = _navigationEvent.asStateFlow()

    private val job = SupervisorJob()
    private val scope = viewModelScope + job

    init {
        loadAnimeList()
    }

    private fun loadAnimeList() {
        scope.launch {
            _itemsUiState.value = AnimeListUiState.Loading
            seasonalAnimeListUseCase.invoke()
                .catch { e ->
                    Log.e(tag, "Error loading anime list", e)
                    _itemsUiState.value =
                        AnimeListUiState.Error("Something went wrong! Please try again later...")
                }
                .collectLatest { animeList ->
                    _itemsUiState.value = AnimeListUiState.Success(animeList)
                }
        }
    }

    fun onItemClick(item: AnimeItem) {
        _navigationEvent.value = item
    }

    fun onResetNavigation() {
        _navigationEvent.value = null
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
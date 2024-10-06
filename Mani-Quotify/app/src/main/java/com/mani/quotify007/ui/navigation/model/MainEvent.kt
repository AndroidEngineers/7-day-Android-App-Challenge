package com.mani.quotify007.ui.navigation.model

import com.mani.quotify007.domain.model.Quote

sealed class MainEvent {
    data class AddFavorite(val quote: Quote) : MainEvent()
    data class RemoveFavorite(val quote: Quote): MainEvent()
    data object GetRandomQuote: MainEvent()
    data class CopyText(val quote: Quote): MainEvent()
    data class ShareClick(val quote: Quote): MainEvent()
    data class ShowToast(val message: String): MainEvent()
}
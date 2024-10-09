package com.mani.quotify007.ui.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mani.quotify007.domain.usecase.GetQuoteUseCase

class QuoteViewModelFactory(
    private val useCase: GetQuoteUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
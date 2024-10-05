package com.example.quotesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.domain.usecases.QuoteUseCase

class QuoteViewModelFactory(private val quoteUseCase: QuoteUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(quoteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.android.engineer.mealmate.view.features.auth.state

import com.android.engineer.mealmate.data.remote.model.response.RegisterResponse

sealed interface VisitingRegisterUiState {
    data object Loading : VisitingRegisterUiState
    data class Error(val error: String) : VisitingRegisterUiState
    data class Success(
        val registerResponse: RegisterResponse? = null
    ) : VisitingRegisterUiState
}
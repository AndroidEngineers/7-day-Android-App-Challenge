package com.android.engineer.mealmate.domain.repository

import com.android.engineer.mealmate.data.remote.model.request.RegisterRequest
import com.android.engineer.mealmate.data.remote.model.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun registerAccount(registerRequest: RegisterRequest): Flow<RegisterResponse>
}
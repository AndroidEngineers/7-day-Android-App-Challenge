package com.android.engineer.mealmate.data.repository

import com.android.engineer.mealmate.data.remote.model.request.RegisterRequest
import com.android.engineer.mealmate.data.remote.model.response.RegisterResponse
import com.android.engineer.mealmate.data.remote.MealAPI
import com.android.engineer.mealmate.data.utils.API_KEY_VALUE
import com.android.engineer.mealmate.repository.remote.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: MealAPI
): AuthRepository {

    override suspend fun registerAccount(registerRequest: RegisterRequest): Flow<RegisterResponse> {
        return flow {
            val response = api.registerAccount(
                registerRequest, API_KEY_VALUE
            )
            emit(response)
            delay(3000L)
        }.map {
            it
        }
    }
}
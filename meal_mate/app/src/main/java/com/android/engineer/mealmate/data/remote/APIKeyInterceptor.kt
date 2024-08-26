package com.android.engineer.mealmate.data.remote

import com.android.engineer.mealmate.data.utils.API_KEY
import com.android.engineer.mealmate.data.utils.API_KEY_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class APIKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentUrl = chain.request().url
        val newUrl = currentUrl.newBuilder().addQueryParameter(API_KEY, API_KEY_VALUE).build()
        val currentRequest = chain.request().newBuilder()
        val newRequest = currentRequest.url(newUrl).build()
        return chain.proceed(newRequest)
    }
}
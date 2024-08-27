package com.android.engineer.mealmate.data.remote

import com.android.engineer.mealmate.data.utils.CONTENT_HEADER_KEY
import com.android.engineer.mealmate.data.utils.CONTENT_HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader(CONTENT_HEADER_KEY, CONTENT_HEADER_VALUE)

        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}
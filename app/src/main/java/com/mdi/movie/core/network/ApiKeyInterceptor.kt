package com.mdi.movie.core.network

import com.mdi.movie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Append the api_key to the request URL
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.IMDB_API_KEY)
            .build()

        // Create a new request with the updated URL
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
package com.example.movieapp.data.retrofit

import com.example.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val apiKey = BuildConfig.API_KEY
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        val url = req.url().newBuilder()
            //.addQueryParameter("Authorization", apiKey)
            .build()
        req = req
            .newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .url(url).build()
        return chain.proceed(req)
    }
}
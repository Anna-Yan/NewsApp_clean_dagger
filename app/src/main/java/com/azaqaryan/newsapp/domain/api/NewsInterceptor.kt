package com.azaqaryan.newsapp.domain.api

import com.azaqaryan.newsapp.data.source.remote.NewsService.Companion.NEWS_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("apiKey", NEWS_API_KEY).
            addQueryParameter("mode", "json").
            addQueryParameter("units", "metric").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
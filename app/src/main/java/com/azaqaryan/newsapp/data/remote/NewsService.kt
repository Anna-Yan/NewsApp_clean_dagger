package com.azaqaryan.newsapp.data.remote

import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.NewsArticleResponse
import com.azaqaryan.newsapp.data.entity.NewsSourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
	@GET("v2/sources")
	suspend fun getSources(
		@Query("apiKey") apiKey: String
	): GeneralResult<NewsSourceResponse>

	@GET("v2/top-headlines")
	suspend fun getArticles(
		@Query("apiKey") apiKey: String,
		@Query("sources") sources: String
	): GeneralResult<NewsArticleResponse>
}
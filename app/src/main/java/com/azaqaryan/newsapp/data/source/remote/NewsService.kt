package com.azaqaryan.newsapp.data.source.remote

import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.ArticlesResponse
import com.azaqaryan.newsapp.data.entity.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

	companion object {
		const val NEWS_API_KEY = "f2cdf0785ad44899bf9b5ec95c86dedb"
	}
	@GET("top-headlines/sources")
	suspend fun getSources(): GeneralResult<SourceResponse>

	@GET("everything")
	suspend fun getArticles(
		@Query("sources") source: String,
		@Query("page") page: Int,
		@Query("pageSize") pageSize: Int
	): GeneralResult<ArticlesResponse>
}
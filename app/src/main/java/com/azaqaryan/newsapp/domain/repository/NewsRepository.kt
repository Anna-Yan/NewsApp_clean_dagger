package com.azaqaryan.newsapp.domain.repository

import com.azaqaryan.newsapp.data.entity.ArticlesResponse
import com.azaqaryan.newsapp.data.entity.SourceResponse
import retrofit2.Response

interface NewsRepository {
	suspend fun getSources(): Response<SourceResponse>
	suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): Response<ArticlesResponse>
}
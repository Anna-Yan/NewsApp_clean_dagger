package com.azaqaryan.newsapp.domain.repository

import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.ArticlesResponse
import com.azaqaryan.newsapp.data.entity.SourceResponse

interface NewsRepository {
	suspend fun getSources(): GeneralResult<SourceResponse>
	suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): GeneralResult<ArticlesResponse>
}
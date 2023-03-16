package com.azaqaryan.newsapp.domain.repository

import com.azaqaryan.newsapp.data.ActionResult
import com.azaqaryan.newsapp.data.entity.ArticlesResponse
import com.azaqaryan.newsapp.data.entity.SourceResponse

interface NewsRepository {
	suspend fun getSources(): ActionResult<SourceResponse>
	suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): ActionResult<ArticlesResponse>
}
package com.azaqaryan.newsapp.domain.repository

import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.domain.entity.ArticlesResponse
import com.azaqaryan.newsapp.domain.entity.News

interface NewsRepository {
	suspend fun getNews(): GeneralResult<List<News>>
	suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): GeneralResult<ArticlesResponse>
}
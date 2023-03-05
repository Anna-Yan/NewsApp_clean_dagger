package com.azaqaryan.newsapp.data.repository

import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.ArticlesResponse
import com.azaqaryan.newsapp.data.entity.SourceResponse
import com.azaqaryan.newsapp.data.source.remote.NewsService
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
	private val newsService: NewsService,
) : NewsRepository {

	override suspend fun getSources(): GeneralResult<SourceResponse> =
		newsService.getSources()

	override suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): GeneralResult<ArticlesResponse> =
		newsService.getArticles(sourceId, page, pageSize)

}
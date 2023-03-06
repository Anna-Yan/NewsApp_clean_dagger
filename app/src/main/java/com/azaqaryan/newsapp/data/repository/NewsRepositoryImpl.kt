package com.azaqaryan.newsapp.data.repository

import com.azaqaryan.newsapp.data.entity.ArticlesResponse
import com.azaqaryan.newsapp.data.entity.SourceResponse
import com.azaqaryan.newsapp.data.source.remote.NewsService
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
	private val newsService: NewsService,
) : NewsRepository {

	override suspend fun getSources(): Response<SourceResponse> =
		newsService.getSources()

	override suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): Response<ArticlesResponse> =
		newsService.getArticles(sourceId, page, pageSize)

}
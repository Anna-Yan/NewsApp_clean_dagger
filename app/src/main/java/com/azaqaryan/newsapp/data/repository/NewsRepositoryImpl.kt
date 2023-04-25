package com.azaqaryan.newsapp.data.repository

import com.azaqaryan.newsapp.common.ActionResult
import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.data.entity.error.NewsAppException
import com.azaqaryan.newsapp.data.source.remote.NewsService
import com.azaqaryan.newsapp.domain.entity.Article
import com.azaqaryan.newsapp.domain.entity.ArticlesResponse
import com.azaqaryan.newsapp.domain.entity.News
import com.azaqaryan.newsapp.data.mapper.ArticleSourceMapper
import com.azaqaryan.newsapp.data.mapper.NewsSourceMapper
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
	private val newsService: NewsService,
) : NewsRepository {

	override suspend fun getNews(): GeneralResult<List<News>> {
		return when (val result = newsService.getNews()) {
			is ActionResult.Success -> {
				val news = NewsSourceMapper.mapFromSourceList((result.data.body()?.sources ?: emptyList()))
				GeneralResult.Success(news)
			}
			is ActionResult.Error -> {
				GeneralResult.Error(e = NewsAppException(result.e.messageResId))
			}
			is ActionResult.Failure -> {
				GeneralResult.Failure(result.e, result.state)
			}
		}
	}

	override suspend fun getArticles(sourceId: String, page: Int, pageSize: Int): GeneralResult<ArticlesResponse> {
		return when (val result = newsService.getArticles(sourceId, page, pageSize)) {
			is ActionResult.Success -> {
				val news = ArticleSourceMapper.mapFromSourceResponse((result.data.body()))
				GeneralResult.Success(news)
			}
			is ActionResult.Error -> {
				GeneralResult.Error(e = NewsAppException(result.e.messageResId))
			}
			is ActionResult.Failure -> {
				GeneralResult.Failure(result.e, result.state)
			}
		}
	}

}
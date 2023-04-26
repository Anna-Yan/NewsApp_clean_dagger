package com.azaqaryan.newsapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.common.CommonStates
import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.data.entity.ArticleSource
import com.azaqaryan.newsapp.data.entity.NewsSource
import com.azaqaryan.newsapp.domain.entity.Article
import com.azaqaryan.newsapp.domain.entity.News
import com.azaqaryan.newsapp.domain.usecase.ArticleUseCase
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase

class FakeNewsItemsUseCase : NewsItemsUseCase, ArticleUseCase {
	private val sources = mutableListOf<News>()
	private val articles = mutableMapOf<String, List<Article>>()

	override suspend fun fetchNews(): GeneralResult<List<News>> {
		return if (sources.isNotEmpty()) {
			GeneralResult.Success(sources)
		} else {
			GeneralResult.Failure(Exception("Sources not found"), CommonStates.NOT_FOUND)
		}
	}

	override suspend fun fetchArticles(sourceId: String): Pager<Int, Article> {
		val articlesList = articles[sourceId]
		val pager = Pager(PagingConfig(pageSize = 20)) {
			FakeArticlePagingSource(articlesList ?: emptyList(), 20)
		}
		return pager
	}

	fun setSources(newSources: List<News>?) {
		sources.clear()
		newSources?.let { sources.addAll(it) }
	}

	fun setArticles(sourceId: String, newArticles: List<Article>?) {
		if (newArticles == null) {
			articles.remove(sourceId)
		} else {
			articles[sourceId] = newArticles
		}
	}

}

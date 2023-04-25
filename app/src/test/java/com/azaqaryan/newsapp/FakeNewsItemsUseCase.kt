package com.azaqaryan.newsapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.common.CommonStates
import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.data.entity.ArticleSource
import com.azaqaryan.newsapp.data.entity.NewsSource
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase

class FakeNewsItemsUseCase : NewsItemsUseCase {
	private val sources = mutableListOf<NewsSource>()
	private val articles = mutableMapOf<String, List<ArticleSource>>()

	override suspend fun fetchNews(): GeneralResult<List<NewsSource>> {
		return if (sources.isNotEmpty()) {
			GeneralResult.Success(sources)
		} else {
			GeneralResult.Failure(Exception("Sources not found"), CommonStates.NOT_FOUND)
		}
	}

	override suspend fun fetchArticles(sourceId: String): Pager<Int, ArticleSource> {
		val articlesList = articles[sourceId]
		val pager = Pager(PagingConfig(pageSize = 20)) {
			FakeArticlePagingSource(articlesList ?: emptyList(), 20)
		}
		return pager
	}

	fun setSources(newSources: List<NewsSource>?) {
		sources.clear()
		newSources?.let { sources.addAll(it) }
	}

	fun setArticles(sourceId: String, newArticles: List<ArticleSource>?) {
		if (newArticles == null) {
			articles.remove(sourceId)
		} else {
			articles[sourceId] = newArticles
		}
	}

}

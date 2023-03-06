package com.azaqaryan.newsapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase

class FakeNewsItemsUseCase : NewsItemsUseCase {
	private val sources = mutableListOf<Source>()
	private val articles = mutableMapOf<String, List<Article>>()

	override suspend fun fetchSources(): GeneralResult<List<Source>> {
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

	fun setSources(newSources: List<Source>?) {
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

package com.azaqaryan.newsapp.data.mapper

import com.azaqaryan.newsapp.data.entity.ArticleSource
import com.azaqaryan.newsapp.data.entity.ArticlesSourceResponse
import com.azaqaryan.newsapp.domain.entity.Article
import com.azaqaryan.newsapp.domain.entity.ArticlesResponse

object ArticleSourceMapper {
	private fun map(articleSource: ArticleSource): Article {
		return Article(
			id = articleSource.id,
			title = articleSource.title ?: "",
			author = articleSource.author ?: "",
			urlToImage = articleSource.urlToImage ?: "",
			description = articleSource.description ?: "",
			publishedAt = articleSource.publishedAt ?: "",
		)
	}

	private fun mapFromSourceList(entities: List<ArticleSource>): List<Article> {
		return entities.map { map(it) }
	}

	fun mapFromSourceResponse(articleSourceResponse: ArticlesSourceResponse?): ArticlesResponse {
		return ArticlesResponse(
			status = articleSourceResponse?.status,
			totalResults = articleSourceResponse?.totalResults,
			articles = mapFromSourceList(articleSourceResponse?.articles?: emptyList()),
		)
	}

}
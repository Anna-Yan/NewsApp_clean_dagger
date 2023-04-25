package com.azaqaryan.newsapp.data.mapper

import com.azaqaryan.newsapp.data.entity.NewsSource
import com.azaqaryan.newsapp.domain.entity.News

object NewsSourceMapper {
	private fun map(apiNewsSource: NewsSource): News {
		return News(
			id = apiNewsSource.id ?: "",
			name = apiNewsSource.name ?: "",
			description = apiNewsSource.description ?: "",
			url = apiNewsSource.url ?: "",
		)
	}

	fun mapFromSourceList(entities: List<NewsSource>): List<News> {
		return entities.map { map(it) }
	}
}
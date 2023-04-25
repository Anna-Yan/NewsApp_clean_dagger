package com.azaqaryan.newsapp.domain.entity

data class Article(
	val title: String?,
	val author: String?,
	val urlToImage: String?,
	val description: String?,
	val publishedAt: String?,
	val id: Long = System.currentTimeMillis()
)

data class ArticlesResponse(
	val status: String?,
	val totalResults: Int?,
	val articles: List<Article>?,
)

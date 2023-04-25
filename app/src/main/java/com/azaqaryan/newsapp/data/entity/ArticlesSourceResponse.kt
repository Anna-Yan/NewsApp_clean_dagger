package com.azaqaryan.newsapp.data.entity

data class ArticlesSourceResponse(
	val status: String?,
	val totalResults: Int?,
	val articles: List<ArticleSource>?,
)
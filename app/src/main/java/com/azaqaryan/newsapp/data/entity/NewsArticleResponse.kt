package com.azaqaryan.newsapp.data.entity

data class NewsArticleResponse(
	val status: String?,
	val totalResults: Int?,
	val articles: List<NewsArticle>?,
	val code: String?,
	val message: String?
)
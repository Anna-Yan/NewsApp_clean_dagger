package com.azaqaryan.newsapp.data.entity

data class ArticlesResponse(
	val status: String?,
	val totalResults: Int?,
	val articles: List<Article>?,
)
package com.azaqaryan.newsapp.data.entity

data class ArticleSource(
	val title: String?,
	val author: String?,
	val urlToImage: String?,
	val description: String?,
	val publishedAt: String?,
	val id: Long = System.currentTimeMillis() // Unique identifier
)
package com.azaqaryan.newsapp.data.entity

data class NewsSourceResponse(
	val status: String?,
	val code: String?,
	val message: String?,
	val sources: List<NewsSource>?,
)
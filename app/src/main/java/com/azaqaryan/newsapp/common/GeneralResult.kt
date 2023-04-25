package com.azaqaryan.newsapp.common

import com.azaqaryan.newsapp.data.entity.error.NewsAppException

sealed class GeneralResult<out T> {
	data class Success<out T>(val data: T) : GeneralResult<T>()
	data class Error(val e: NewsAppException) : GeneralResult<Nothing>()
	data class Failure(val e: Throwable, val state: CommonStates) : GeneralResult<Nothing>()
}
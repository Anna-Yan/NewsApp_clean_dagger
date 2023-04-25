package com.azaqaryan.newsapp.common

import com.azaqaryan.newsapp.data.entity.error.NewsAppException
import retrofit2.Response

sealed class ActionResult<out T> {
	data class Success<T>(val data: Response<T>) : ActionResult<T>()
	data class Error(val e: NewsAppException) : ActionResult<Nothing>()
	data class Failure(val e: Throwable, val state: CommonStates) : ActionResult<Nothing>()
}
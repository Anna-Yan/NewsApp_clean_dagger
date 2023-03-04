package com.azaqaryan.newsapp.data

import retrofit2.Response

sealed class GeneralResult<out T> {
	data class Success<T>(val data: Response<T>) : GeneralResult<T>()
	data class Failure(val e: Throwable, val state: CommonStates) : GeneralResult<Nothing>()
}
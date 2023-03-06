package com.azaqaryan.newsapp

import retrofit2.Response

sealed class GeneralResult<out T> {
	data class Success<T>(val data: T) : GeneralResult<T>()
	data class Failure(val e: Throwable, val state: CommonStates) : GeneralResult<Nothing>()

}
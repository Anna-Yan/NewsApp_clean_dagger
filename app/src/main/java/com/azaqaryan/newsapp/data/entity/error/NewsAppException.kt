package com.azaqaryan.newsapp.data.entity.error

import com.azaqaryan.newsapp.common.CommonStates

class NewsAppException(
	val messageResId: Int? = null,
	override val message: String? = null,
	val state: CommonStates = CommonStates.NOT_FOUND
) : Exception(message)

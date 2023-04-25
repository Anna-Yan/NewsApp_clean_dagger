package com.azaqaryan.newsapp.domain.usecase

import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.domain.entity.News

interface NewsItemsUseCase {
	suspend fun fetchNews(): GeneralResult<List<News>>
}
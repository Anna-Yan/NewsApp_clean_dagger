package com.azaqaryan.newsapp.domain.usecase

import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.di.IoDispatcher
import com.azaqaryan.newsapp.domain.entity.News
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsItemsUseCaseImpl @Inject constructor(
	@IoDispatcher private val dispatcher: CoroutineDispatcher,
	private val newsRepository: NewsRepository,
) : NewsItemsUseCase {

	override suspend fun fetchNews(): GeneralResult<List<News>> =
		withContext(dispatcher) {
			newsRepository.getNews()
		}

}
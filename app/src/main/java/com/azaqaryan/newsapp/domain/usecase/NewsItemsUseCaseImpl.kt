package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.data.ActionResult
import com.azaqaryan.newsapp.R
import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.data.entity.error.NewsAppException
import com.azaqaryan.newsapp.data.paging.ArticlesPagingSource
import com.azaqaryan.newsapp.di.IoDispatcher
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsItemsUseCaseImpl @Inject constructor(
	@IoDispatcher private val dispatcher: CoroutineDispatcher,
	private val newsRepository: NewsRepository,
) : NewsItemsUseCase {

	override suspend fun fetchSources(): GeneralResult<List<Source>> =
		withContext(dispatcher) {
			when (val result = newsRepository.getSources()) {
				is ActionResult.Success -> {
					return@withContext GeneralResult.Success(result.data.body()?.sources ?: emptyList())
				}
				is ActionResult.Error -> {
					GeneralResult.Error(NewsAppException(R.string.news_error))
				}
				is ActionResult.Failure -> {
					GeneralResult.Failure(result.e, result.state)
				}
			}
		}
	override suspend fun fetchArticles(sourceId: String): Pager<Int, Article> =
		Pager(
			config = PagingConfig(pageSize = LIST_PAGE_SIZE, enablePlaceholders = false),
			pagingSourceFactory = { ArticlesPagingSource(sourceId, newsRepository) },
			initialKey = INITIAL_PAGE_KEY
		)

	companion object {
		private const val LIST_PAGE_SIZE = 10
		private const val INITIAL_PAGE_KEY = 1
	}

}
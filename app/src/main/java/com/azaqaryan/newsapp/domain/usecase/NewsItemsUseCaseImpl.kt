package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.CommonStates
import com.azaqaryan.newsapp.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.data.entity.SourceResponse
import com.azaqaryan.newsapp.data.paging.ArticlesPagingSource
import com.azaqaryan.newsapp.di.IoDispatcher
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class NewsItemsUseCaseImpl @Inject constructor(
	@IoDispatcher private val dispatcher: CoroutineDispatcher,
	private val newsRepository: NewsRepository,
) : NewsItemsUseCase {

	override suspend fun fetchSources(): GeneralResult<List<Source>> =
		withContext(dispatcher) {
			val response = newsRepository.getSources()
			if (response.isSuccessful) {
				return@withContext GeneralResult.Success(response.body()?.sources ?: emptyList())
			} else {
				return@withContext GeneralResult.Failure(IOException("Error getting news"), CommonStates.NOT_FOUND)
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
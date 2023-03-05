package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.SourceResponse
import com.azaqaryan.newsapp.data.paging.ArticlesPagingSource
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsItemsUseCaseImpl @Inject constructor(
	private val newsRepository: NewsRepository,
) : NewsItemsUseCase {

	override suspend fun fetchNews(): GeneralResult<SourceResponse> =
		newsRepository.getSources()

	override suspend fun fetchArticles(sourceId: String): Pager<Int, Article> =
		Pager(
			config = PagingConfig(pageSize = LIST_PAGE_SIZE),
			pagingSourceFactory = { ArticlesPagingSource(sourceId, newsRepository) }
		)

	private fun pgConfig(): PagingConfig {
		return PagingConfig(
			pageSize = LIST_PAGE_SIZE,
			enablePlaceholders = false,
			maxSize = 3 * LIST_PAGE_SIZE,
			initialLoadSize = LIST_PAGE_SIZE
		)
	}

	companion object {
		private const val LIST_PAGE_SIZE = 20
	}

}
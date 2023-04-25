package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azaqaryan.newsapp.data.paging.ArticlesPagingSource
import com.azaqaryan.newsapp.di.IoDispatcher
import com.azaqaryan.newsapp.domain.entity.Article
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ArticleUseCaseImpl @Inject constructor(
	@IoDispatcher private val dispatcher: CoroutineDispatcher,
	private val newsRepository: NewsRepository,
) : ArticleUseCase {

	override suspend fun fetchArticles(sourceId: String): Pager<Int, Article> =
		Pager(
			config = PagingConfig(pageSize = LIST_PAGE_SIZE, enablePlaceholders = false),
			pagingSourceFactory = { ArticlesPagingSource(sourceId, newsRepository) },
			initialKey = INITIAL_PAGE_KEY
		)

	companion object {
		private const val LIST_PAGE_SIZE = 20
		private const val INITIAL_PAGE_KEY = 1
	}

}
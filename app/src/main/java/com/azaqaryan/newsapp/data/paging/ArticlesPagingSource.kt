package com.azaqaryan.newsapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.domain.repository.NewsRepository

class ArticlesPagingSource(
	private val sourceId: String,
	private val newsRepository: NewsRepository,
) : PagingSource<Int, Article>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
		val page = params.key ?: 1
		val pageSize = params.loadSize

		return when (val response = newsRepository.getArticles(sourceId, page, pageSize)) {
			is GeneralResult.Success -> {
				val result = response.data.body()
				val articles = result?.articles ?: listOf()
				LoadResult.Page(
					data = articles,
					prevKey = if (page == 1) null else page - 1,
					nextKey = if (articles.isEmpty()) null else page + 1
				)
			}
			is GeneralResult.Failure ->
				LoadResult.Error(response.e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
		}
	}
}

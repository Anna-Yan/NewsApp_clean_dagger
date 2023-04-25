package com.azaqaryan.newsapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.domain.entity.Article
import com.azaqaryan.newsapp.domain.repository.NewsRepository

class ArticlesPagingSource(
	private val sourceId: String,
	private val newsRepository: NewsRepository,
) : PagingSource<Int, Article>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
		val page = params.key ?: 1
		val pageSize = params.loadSize
		Log.d(TAG, "pageSize= $pageSize")

		when (val response = newsRepository.getArticles(sourceId, page, pageSize)) {
			is GeneralResult.Success -> {
				Log.d(TAG, "sourceId= $sourceId")
				Log.d(TAG, "current page= $page")
				Log.d(TAG, "totalResults = ${response.data.totalResults} ")
				val result = response.data
				val articles = result.articles ?: listOf()

				val totalPages =
					result?.totalResults?.let { kotlin.math.ceil(it.toDouble() / pageSize).toInt() } ?: 1
				Log.d(TAG, "totalPages = $totalPages")

				val prevKey = if (page == 1) null else page - 1
				val nextKey = if (page < totalPages) page + 1 else null

				return LoadResult.Page(
					data = articles,
					prevKey = prevKey,
					nextKey = nextKey
				)
			}
			is GeneralResult.Failure ->
				return LoadResult.Error(response.e)

			is GeneralResult.Error ->
				return LoadResult.Error(response.e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
		}
	}

	companion object {
		private const val TAG = "ArticlePagingSource"
	}

}

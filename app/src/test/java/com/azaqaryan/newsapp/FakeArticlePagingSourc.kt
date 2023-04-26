package com.azaqaryan.newsapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azaqaryan.newsapp.domain.entity.Article

class FakeArticlePagingSource(
	private val articles: List<Article>,
	private val pageSize: Int
) : PagingSource<Int, Article>() {

	override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
		return state.anchorPosition
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
		return try {
			val page = params.key ?: 1
			val startIndex = (page - 1) * pageSize
			val endIndexExclusive = startIndex + pageSize
			val articlesPage = articles.subList(startIndex, endIndexExclusive)

			LoadResult.Page(
				data = articlesPage,
				prevKey = if (page == 1) null else page - 1,
				nextKey = if (articlesPage.isEmpty()) null else page + 1
			)
		} catch (exception: Exception) {
			LoadResult.Error(exception)
		}
	}
}

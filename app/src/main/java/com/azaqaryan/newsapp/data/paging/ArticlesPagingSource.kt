package com.azaqaryan.newsapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.domain.repository.NewsRepository

class ArticlesPagingSource(
	private val sourceId: String,
	private val newsRepository: NewsRepository,
) : PagingSource<Int, Article>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
		return try {
			val page = params.key ?: 1
			val pageSize = params.loadSize
			Log.d("anna", "pageSize= $pageSize" )

			val response = newsRepository.getArticles(sourceId, page, pageSize)
			Log.d("anna", "sourceId= $sourceId" )
			Log.d("anna", "current page= $page" )
			Log.d("anna", "totalResults = ${response.body()?.totalResults} " )
			val result = response.body()
			val articles = result?.articles ?: listOf()

			val nextPage = if (hasMorePages(result?.totalResults ?: 0, page)) page + 1 else null

			LoadResult.Page(
				data = articles,
				prevKey = if (page == 1) null else page - 1,
				nextKey = nextPage
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	private fun hasMorePages(totalPages: Int, currentPage: Int): Boolean {
		return currentPage < totalPages
	}
	override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
		}
	}
}

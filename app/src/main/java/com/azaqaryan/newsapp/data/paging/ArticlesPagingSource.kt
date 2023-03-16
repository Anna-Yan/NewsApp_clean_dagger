package com.azaqaryan.newsapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azaqaryan.newsapp.data.ActionResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.domain.repository.NewsRepository

class ArticlesPagingSource(
	private val sourceId: String,
	private val newsRepository: NewsRepository,
) : PagingSource<Int, Article>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
			val page = params.key ?: 1
			val pageSize = params.loadSize
			Log.d("anna", "pageSize= $pageSize" )

			when(val response = newsRepository.getArticles(sourceId, page, pageSize)){
				is ActionResult.Success -> {
					Log.d("anna", "sourceId= $sourceId")
					Log.d("anna", "current page= $page")
					Log.d("anna", "totalResults = ${response.data.body()?.totalResults} ")
					val result = response.data.body()
					val articles = result?.articles ?: listOf()

					val totalPages = result?.totalResults?.let { kotlin.math.ceil(it.toDouble() / pageSize).toInt() } ?: 1
					val prevKey = if (page == 1) null else page - 1
					val nextKey = if (page < totalPages) page + 1 else null
					Log.d("anna", "totalPages = $totalPages")

				return LoadResult.Page(
						data = articles,
						prevKey = prevKey,
						nextKey = nextKey
					)
				}
				is ActionResult.Failure ->
					return LoadResult.Error(response.e)

				is ActionResult.Error ->
					return LoadResult.Error(response.e)
			}
	}

	override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
		}
	}
}

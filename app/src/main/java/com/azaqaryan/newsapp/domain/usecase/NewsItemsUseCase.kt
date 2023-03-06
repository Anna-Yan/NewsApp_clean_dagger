package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import com.azaqaryan.newsapp.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.data.entity.SourceResponse

interface NewsItemsUseCase {
	suspend fun fetchSources(): GeneralResult<List<Source>>
	suspend fun fetchArticles(sourceId: String): Pager<Int, Article>
}
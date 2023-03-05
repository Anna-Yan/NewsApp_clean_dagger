package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.data.entity.SourceResponse

interface NewsItemsUseCase {
	suspend fun fetchNews(): GeneralResult<SourceResponse>
	suspend fun fetchArticles(sourceId: String): Pager<Int, Article>
}
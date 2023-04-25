package com.azaqaryan.newsapp.domain.usecase

import androidx.paging.Pager
import com.azaqaryan.newsapp.domain.entity.Article

interface ArticleUseCase {
	suspend fun fetchArticles(sourceId: String): Pager<Int, Article>
}
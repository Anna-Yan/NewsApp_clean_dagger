package com.azaqaryan.newsapp.di

import com.azaqaryan.newsapp.domain.repository.NewsRepository
import com.azaqaryan.newsapp.domain.usecase.ArticleUseCase
import com.azaqaryan.newsapp.domain.usecase.ArticleUseCaseImpl
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
object NewsAndArticlesModule {

	@Provides
	fun provideNewsUseCase(@IoDispatcher dispatcher: CoroutineDispatcher, newsRepository: NewsRepository): NewsItemsUseCase {
		return NewsItemsUseCaseImpl(dispatcher, newsRepository)
	}

	@Provides
	fun provideArticleUseCase(@IoDispatcher dispatcher: CoroutineDispatcher, newsRepository: NewsRepository): ArticleUseCase {
		return ArticleUseCaseImpl(dispatcher, newsRepository)
	}
}

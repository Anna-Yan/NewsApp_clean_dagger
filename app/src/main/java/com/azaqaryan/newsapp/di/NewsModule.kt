package com.azaqaryan.newsapp.di

import com.azaqaryan.newsapp.domain.repository.NewsRepository
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object NewsModule {

	@Provides
	fun provideNewsUseCase(@IoDispatcher dispatcher: CoroutineDispatcher, newsRepository: NewsRepository): NewsItemsUseCase {
		return NewsItemsUseCaseImpl(dispatcher, newsRepository)
	}

}

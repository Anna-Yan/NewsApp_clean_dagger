package com.azaqaryan.newsapp.di

import com.azaqaryan.newsapp.data.repository.NewsRepositoryImpl
import com.azaqaryan.newsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

	@Suppress("FunctionName")
	@Binds
	fun bindNewsRepositoryImpl_to_NewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}
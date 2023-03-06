package com.azaqaryan.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
	private val sourceId: String,
	private val newsUseCase: NewsItemsUseCase,
) : ViewModel() {

	suspend fun fetchArticles(): Flow<PagingData<Article>> =
		newsUseCase.fetchArticles(sourceId).flow.cachedIn(
			viewModelScope
		).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


	class ArticlesFactory @AssistedInject constructor(
		@Assisted("sourceId") private val sourceId: String,
		private val newsUseCase: NewsItemsUseCase,
	) : ViewModelProvider.Factory {

		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return when {
				modelClass.isAssignableFrom(ArticlesViewModel::class.java) ->
					ArticlesViewModel(sourceId, newsUseCase) as T
				else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
			}
		}
		@AssistedFactory
		interface Factory{
			fun create(@Assisted("sourceId") sourceId: String): ArticlesFactory
		}
	}
}

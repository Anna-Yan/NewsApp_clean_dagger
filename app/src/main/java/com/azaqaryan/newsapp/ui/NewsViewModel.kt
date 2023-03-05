package com.azaqaryan.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.azaqaryan.newsapp.data.CommonStates
import com.azaqaryan.newsapp.data.GeneralResult
import com.azaqaryan.newsapp.data.entity.Article
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
	private val newsUseCase: NewsItemsUseCase,
) : ViewModel() {

	private val _state = MutableStateFlow(CommonStates.NORMAL)
	val state
		get() = _state.asStateFlow()

	private val _sources = MutableStateFlow<List<Source>>(emptyList())
	val sources: StateFlow<List<Source>>
		get() = _sources

	private val _articles = MutableStateFlow<PagingData<Article>>(PagingData.empty())
	val articles: StateFlow<PagingData<Article>>
		get() = _articles

	private fun setState(commonStates: CommonStates) {
		_state.value = commonStates
	}

	fun fetchSources() {
		viewModelScope.launch {
			setState(CommonStates.PROGRESS)
			when (val result = newsUseCase.fetchSources()) {
				is GeneralResult.Success -> {
					result.data.body()?.sources?.let {
						_sources.value = it
					}
				}
				is GeneralResult.Failure -> {
					setState(CommonStates.NOT_FOUND)
				}
			}
		}
	}

	suspend fun fetchArticles(sourceId: String): Flow<PagingData<Article>> =
		newsUseCase.fetchArticles(sourceId).flow.cachedIn(
			viewModelScope
		).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

}

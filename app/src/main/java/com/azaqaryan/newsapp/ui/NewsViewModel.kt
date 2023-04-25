package com.azaqaryan.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.azaqaryan.newsapp.common.CommonStates
import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.data.entity.NewsSource
import com.azaqaryan.newsapp.domain.entity.News
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase
import com.azaqaryan.newsapp.ui.news.NewsFragmentDirections
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
	private val navController: NavController,
	private val newsUseCase: NewsItemsUseCase,
) : ViewModel() {

	private val _state = MutableStateFlow(CommonStates.NORMAL)
	val state
		get() = _state.asStateFlow()

	private val _sources = MutableStateFlow<List<News>>(emptyList())
	val sources: StateFlow<List<News>>
		get() = _sources

	private fun setState(commonStates: CommonStates) {
		_state.value = commonStates
	}

	init {
		fetchSources()
	}

	private fun fetchSources() {
		viewModelScope.launch {
			setState(CommonStates.PROGRESS)
			when (val result = newsUseCase.fetchNews()) {
				is GeneralResult.Success -> {
					result.data.let {
						_sources.value = it
					}
					setState(CommonStates.NORMAL)
				}
				is GeneralResult.Error -> {
					setState(CommonStates.NOT_FOUND)
				}
				is GeneralResult.Failure -> {
					setState(CommonStates.NO_CONNECTION)
				}
			}
		}
	}

	fun navigateToArticleScreen(id: String) {
		val action = NewsFragmentDirections.actionNewsFragmentToArticlesFragment(id)
		navController.navigate(action)
	}

	class Factory @Inject constructor(
		private val navController: NavController,
		private val newsItemsUseCase: NewsItemsUseCase,
	) : ViewModelProvider.Factory {

		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return when {
				modelClass.isAssignableFrom(NewsViewModel::class.java) ->
					NewsViewModel(navController, newsItemsUseCase) as T
				else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
			}
		}
	}

}

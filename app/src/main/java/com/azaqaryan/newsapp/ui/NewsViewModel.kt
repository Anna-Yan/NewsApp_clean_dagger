package com.azaqaryan.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azaqaryan.newsapp.CommonStates
import com.azaqaryan.newsapp.GeneralResult
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

	private fun setState(commonStates: CommonStates) {
		_state.value = commonStates
	}

	fun fetchSources() {
		viewModelScope.launch {
			setState(CommonStates.PROGRESS)
			when (val result = newsUseCase.fetchSources()) {
				is GeneralResult.Success -> {
					result.data.let {
						_sources.value = it
					}
					setState(CommonStates.NORMAL)
				}
				is GeneralResult.Failure -> {
					setState(CommonStates.NOT_FOUND)
				}
			}
		}
	}

	class Factory @Inject constructor(
		private val newsItemsUseCase: NewsItemsUseCase,
	) : ViewModelProvider.Factory {

		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return when {
				modelClass.isAssignableFrom(NewsViewModel::class.java) ->
					NewsViewModel(newsItemsUseCase) as T
				else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
			}
		}
	}

}

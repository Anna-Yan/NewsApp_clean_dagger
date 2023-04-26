package com.azaqaryan.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

	private val _navigation = MutableStateFlow<NavigationEvent?>(null)
	val navigation: StateFlow<NavigationEvent?> get() = _navigation

	fun navigate(navDirections: NavDirections) {
		_navigation.value = NavigationEvent.ToDirection(navDirections)
	}

	fun navigateBack() {
		_navigation.value = NavigationEvent.Back
	}

	fun clear(){
		_navigation.value = null
	}
}
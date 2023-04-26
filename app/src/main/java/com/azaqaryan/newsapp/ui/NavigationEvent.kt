package com.azaqaryan.newsapp.ui

import androidx.navigation.NavDirections

sealed class NavigationEvent {
	data class ToDirection(val directions: NavDirections) : NavigationEvent()
	object Back : NavigationEvent()
}
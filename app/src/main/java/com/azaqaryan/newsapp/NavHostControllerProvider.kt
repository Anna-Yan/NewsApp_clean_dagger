package com.azaqaryan.newsapp

import androidx.navigation.NavController

interface NavHostControllerProvider {
	fun provideNavController(): NavController
}
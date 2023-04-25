package com.azaqaryan.newsapp.di

import androidx.navigation.NavController
import com.azaqaryan.newsapp.NavHostControllerProvider
import dagger.Module
import dagger.Provides

@Module
class NavModule(private val navHostControllerProvider: NavHostControllerProvider) {

	@Provides
	fun provideNavController(): NavController {
		return navHostControllerProvider.provideNavController()
	}
}


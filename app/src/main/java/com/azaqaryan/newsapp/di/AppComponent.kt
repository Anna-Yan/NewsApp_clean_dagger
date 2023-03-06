package com.azaqaryan.newsapp.di

import com.azaqaryan.newsapp.ui.news.ArticlesFragment
import com.azaqaryan.newsapp.ui.news.NewsFragment
import com.azaqaryan.newsapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
	fun inject(activity: MainActivity)
	fun inject(fragment: NewsFragment)
	fun inject(fragment: ArticlesFragment)
}

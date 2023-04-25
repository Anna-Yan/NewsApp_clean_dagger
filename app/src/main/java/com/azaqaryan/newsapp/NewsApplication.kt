package com.azaqaryan.newsapp

import android.app.Application
import android.content.Context
import com.azaqaryan.newsapp.di.AppComponent

class NewsApplication : Application() {
	lateinit var appComponent: AppComponent
		private set

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.create()
	}
}

val Context.appComponent: AppComponent
	get() = when (this) {
		is NewsApplication -> appComponent
		else -> this.applicationContext.appComponent
	}

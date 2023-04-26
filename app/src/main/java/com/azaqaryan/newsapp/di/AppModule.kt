package com.azaqaryan.newsapp.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class, NewsAndArticlesModule::class, DispatcherModule::class])
class AppModule
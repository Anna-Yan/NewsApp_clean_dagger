package com.azaqaryan.newsapp.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class, NewsModule::class, DispatcherModule::class])
class AppModule
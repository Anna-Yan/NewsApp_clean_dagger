package com.azaqaryan.newsapp.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule
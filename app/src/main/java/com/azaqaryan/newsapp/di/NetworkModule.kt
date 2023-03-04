package com.azaqaryan.newsapp.di

import com.azaqaryan.newsapp.BuildConfig
import com.azaqaryan.newsapp.data.source.remote.NewsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().apply {
				level = HttpLoggingInterceptor.Level.BODY
			})
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	@Provides
	@Singleton
	fun provideNewsService(retrofit: Retrofit): NewsService {
		return retrofit.create(NewsService::class.java)
	}
}

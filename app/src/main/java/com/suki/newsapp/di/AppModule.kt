package com.suki.newsapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.suki.newsapp.remote.NewsDataSource
import com.suki.newsapp.remote.NewsService
import com.suki.newsapp.repository.NewsRepository
import com.suki.newsapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Provides
    fun provideRepository(newsDataSource: NewsDataSource): NewsRepository = NewsRepository(newsDataSource)

    @Provides
    fun provideNewsDataSource (newsService: NewsService): NewsDataSource = NewsDataSource(newsService)

}
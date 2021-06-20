package com.suki.newsapp.repository

import com.suki.newsapp.entities.NewsData
import com.suki.newsapp.remote.NewsDataSource
import com.suki.newsapp.util.Resource
import javax.inject.Inject

/**
 * Repository class to get the ddata from the API
 */
class NewsRepository @Inject constructor(private val remoteDataSource: NewsDataSource) {

    suspend fun getNews(api_key: String, country: String, page_no:Int): Resource<NewsData> {
        return remoteDataSource.getNews(api_key, country, page_no)
    }

}
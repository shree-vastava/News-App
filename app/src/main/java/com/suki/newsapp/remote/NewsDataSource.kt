package com.suki.newsapp.remote

import javax.inject.Inject

/**
 * This class contains all the API calls
 * and hence the Data source.
 * All the API calls are made using the getResult function
 * of BaseDataSource
 * which is generic and returns the processed object based
 * on success and failure
 *
 * So success/failure of API is already handled
 */

class NewsDataSource @Inject constructor(private val newsService: NewsService): BaseDataSource() {

    suspend fun getNews(api_key:String, country: String, page_no: Int) = getResult { newsService.getNews(api_key, country, page_no) }
}
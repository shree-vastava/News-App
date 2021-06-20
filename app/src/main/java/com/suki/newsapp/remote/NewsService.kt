package com.suki.newsapp.remote

import com.suki.newsapp.entities.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("articlesearch.json")
    suspend fun getNews(@Query("api-key") apiKey:String, @Query("q") country :String, @Query("page") page: Int) : Response<NewsData>
}
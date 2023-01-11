package com.example.newsapp.data.network.service

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Sources
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.NAP_KEY

interface NewsApiService {
    @GET("everything")
    suspend fun getAllNews(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<News>

    @GET("everything")
    suspend fun getNewsByCategory(
        @Query("q") newsCategory: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<News>

    @GET("sources")
    suspend fun getNewsFromSource(
        @Query("apiKey") apiKey: String = API_KEY
    ):  Response<Sources>
}

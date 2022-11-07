package com.example.newsapp.data.network.service

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.network.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.NAP_KEY

interface NewsApiService {
    @GET("everything?apiKey=$API_KEY")
    suspend fun getAllNews(@Query("q") q: String, @Query("page") page : Int) :Response<News>
}

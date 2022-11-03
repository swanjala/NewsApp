package com.example.newsapp

import com.example.newsapp.network.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.NAP_KEY

interface NewsApiService {
    @GET("everything?apiKey=$API_KEY")
    suspend fun getAllNews(@Query("q") q: String, @Query("page") page : Int) :Response<News>
}
package com.example.newsapp.data.api

import com.example.newsapp.data.datamodels.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("everything")
    fun getAllNews(@Query("q") query: String,
                   @Query("apiKey") apiKey: String): Call<DataResponse>

    @GET("everything")
    fun getNewsByDomains(@Query("domains") domains: String,
                         @Query("apiKey") apiKey: String): Call<DataResponse>

    @GET("sources")
    fun getSources(@Query("apiKey") apiKey: String): Call<DataResponse>

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("apiKey") apiKey: String): Call<DataResponse>

    @GET("top-headlines")
    fun getTopHeadlinesByCountryCategory(@Query("country") country: String,
                                         @Query("category") category: String,
                                         @Query("apiKey") apiKey: String): Call<DataResponse>

    @GET("top-headlines")
    fun getTopHeadlineBySearch(@Query("q") query: String,
                               @Query("apiKey") apiKey: String): Call<DataResponse>


}

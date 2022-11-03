package com.example.newsapp.news.model

import android.util.Log
import com.example.newsapp.NewsApi
import com.example.newsapp.network.News
import javax.inject.Inject

interface NewsRemoteDataStore {
    suspend fun getAllNews(keyWord: String?): News?
}

class NewsRemoteDataStoreImpl @Inject constructor() : NewsRemoteDataStore {
    private val apiService = NewsApi.retrofitService

    override suspend fun getAllNews(keyWord: String?): News? = try {
        val response = apiService.getAllNews(keyWord ?: "keyword", 1)
        response.body()

    } catch (t: Throwable) {
        Log.d("Failure", "Failure ${t.message}")
        null
    }
}
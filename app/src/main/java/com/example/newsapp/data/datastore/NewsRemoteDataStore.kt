package com.example.newsapp.news.module

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.newsapp.data.model.News
import com.example.newsapp.data.network.service.NewsApiService
import javax.inject.Inject

interface NewsRemoteDataStore {
    suspend fun getAllNews(keyWord: String?): News?
}

class NewsRemoteDataStoreImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataStore {

    @WorkerThread
    override suspend fun getAllNews(keyWord: String?): News? = try {
        val response = newsApiService.getAllNews(keyWord ?: "keyword", 1)
        response.body()

    } catch (t: Throwable) {
        Log.d("Failure", "Failure ${t.message}")
        null
    }
}
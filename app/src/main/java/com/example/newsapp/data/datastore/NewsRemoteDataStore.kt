package com.example.newsapp.data.datastore

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.newsapp.data.model.News
import com.example.newsapp.data.network.service.NewsApiService
import javax.inject.Inject

interface NewsRemoteDataStore {
    suspend fun getNewsByCategory(category: String, pageNumber: Int): News?
}

class NewsRemoteDataStoreImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataStore {

    @WorkerThread
    override suspend fun getNewsByCategory(category: String, pageNumber: Int): News? = try {
        val response = newsApiService.getNewsByCategory(category, pageNumber) // page is hardcoded
        response.body()
    } catch (error: Throwable) {

        Log.getStackTraceString(error)
        null
    }
}

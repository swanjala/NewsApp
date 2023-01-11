package com.example.newsapp.data.datastore

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Sources
import com.example.newsapp.data.network.service.NewsApiService
import javax.inject.Inject

interface NewsRemoteDataStore {
    suspend fun getNewsByCategory(category: String): News?
    suspend fun getNewsFromSource(): Sources?
    suspend fun getAllNews(): News?
}

class NewsRemoteDataStoreImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataStore {

    @WorkerThread
    override suspend fun getNewsByCategory(category: String): News? = try {
        val response = newsApiService.getNewsByCategory(category, 1)
        response.body()
    } catch (error:Throwable) {
        Log.getStackTraceString(error)
        null
    }

    @WorkerThread
    override suspend fun getNewsFromSource(): Sources?  = try {
        val response = newsApiService.getNewsFromSource()
        response.body()
    } catch (error: Throwable) {
        Log.getStackTraceString(error)
        null
    }

    override suspend fun getAllNews(): News?  = try{
        val response = newsApiService.getAllNews("all",1)
        response.body()
    } catch (error: Throwable) {
        Log.getStackTraceString(error)
        null
    }
}

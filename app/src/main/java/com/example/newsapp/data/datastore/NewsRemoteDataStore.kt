package com.example.newsapp.data.datastore

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Sources
import com.example.newsapp.data.network.service.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface NewsRemoteDataStore {
    suspend fun getNewsByCategory(category: String): News?
    suspend fun getNewsFromSource(): Sources?
    suspend fun getAllNews(): Flow<News?>
}

const val requestPath = "all"

class NewsRemoteDataStoreImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataStore {

    @WorkerThread
    override suspend fun getNewsByCategory(category: String): News? = try {
        val response = newsApiService.getNewsByCategory(category, 1)
        response.body()
    } catch (error: Throwable) {
        Log.getStackTraceString(error)
        null
    }

    @WorkerThread
    override suspend fun getNewsFromSource(): Sources? = try {
        val response = newsApiService.getNewsFromSource()
        response.body()
    } catch (error: Throwable) {
        Log.getStackTraceString(error)
        null
    }

    override suspend fun getAllNews(): Flow<News?> = flow {
        try {
            emit(newsApiService.getAllNews(requestPath, 1).body())
        } catch (error: Throwable) {
            Log.getStackTraceString(error)
            emptyFlow<News?>()
        }
    }.flowOn(Dispatchers.Default)
}

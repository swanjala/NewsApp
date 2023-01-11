package com.example.newsapp.data.datastore

import android.util.Log
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Sources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DataRepository {
    suspend fun getSavedArticles(): Flow<List<Article>>
    suspend fun insertNewArticle(article: Article): Boolean
    suspend fun getNewsByCategory(category: String): News?
    suspend fun getNewsFromDataSource(): Sources?
    suspend fun getAllNews() : News?
}

class DataRepositoryImpl @Inject constructor(
    private val newsRemoteDataStore: NewsRemoteDataStore,
    private val newsLocalDataStore: NewsLocalDataStore
) : DataRepository {
    override suspend fun getNewsByCategory(category: String): News? {
        return newsRemoteDataStore.getNewsByCategory(category)
    }

    override suspend fun getNewsFromDataSource(): Sources? {
        return newsRemoteDataStore.getNewsFromSource()
    }

    override suspend fun getAllNews(): News? {
        return newsRemoteDataStore.getAllNews()
    }

    override suspend fun getSavedArticles(): Flow<List<Article>> {
        return newsLocalDataStore.getSavedArticles()
    }

    override suspend fun insertNewArticle(article: Article): Boolean {
        try {
            newsLocalDataStore.insert(article)
        } catch (error: Throwable) {
            Log.getStackTraceString(error)
            return false
        }
        return true
    }
}

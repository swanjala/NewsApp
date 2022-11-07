package com.example.newsapp.data.datastore

import androidx.annotation.WorkerThread
import com.example.newsapp.data.database.ArticleDao
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NewsLocalDataStore {
    suspend fun insert(article: Article)
    suspend fun getSavedArticles(): Flow<List<Article>>
}

class NewsLocalDataStoreImpl @Inject constructor(
    private val articleDao: ArticleDao
) : NewsLocalDataStore {

    @WorkerThread
    override suspend fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getSavedArticles()
    }

    @WorkerThread
    override suspend fun insert(article: Article) {
        articleDao.insert(article)
    }
}
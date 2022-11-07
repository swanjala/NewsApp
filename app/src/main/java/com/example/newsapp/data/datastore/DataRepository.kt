package com.example.newsapp.news.module

import com.example.newsapp.data.datastore.NewsLocalDataStore
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

interface DataRepository {
    suspend fun getNewsData(): News?
    suspend fun getSavedArticles(): Flow<List<Article>>
    suspend fun insertNewArticle(article: Article)
}

class DataRepositoryImpl @Inject constructor(
    private val newsRemoteDataStore: NewsRemoteDataStore,
    private val newsLocalDataStore: NewsLocalDataStore
): DataRepository {
    override suspend fun getNewsData(): News? {
        return newsRemoteDataStore.getAllNews("keyword")
    }

    override suspend fun getSavedArticles(): Flow<List<Article>> {
        return newsLocalDataStore.getSavedArticles()
    }

    override suspend fun insertNewArticle(article: Article) {
        newsLocalDataStore.insert(article)
    }
}

package com.example.newsapp.news.model

import com.example.newsapp.network.News
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNewsData():News?
}

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataStore: NewsRemoteDataStore
): NewsRepository {
    override suspend fun getNewsData(): News? {
        return newsRemoteDataStore.getAllNews("keyword")
    }
}
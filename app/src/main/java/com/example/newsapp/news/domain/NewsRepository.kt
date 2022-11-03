package com.example.newsapp.news.module

import com.example.newsapp.network.model.News
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNewsData(): News?
}

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataStore: NewsRemoteDataStore
): NewsRepository {
    override suspend fun getNewsData(): News? {
        return newsRemoteDataStore.getAllNews("keyword")
    }
}

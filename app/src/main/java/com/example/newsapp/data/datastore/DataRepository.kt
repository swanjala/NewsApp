package com.example.newsapp.news.module

import com.example.newsapp.data.network.model.News
import javax.inject.Inject

interface DataRepository {
    suspend fun getNewsData(): News?
}

class DataRepositoryImpl @Inject constructor(
    private val newsRemoteDataStore: NewsRemoteDataStore
): DataRepository {
    override suspend fun getNewsData(): News? {
        return newsRemoteDataStore.getAllNews("keyword")
    }
}

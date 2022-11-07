package com.example.newsapp.dependencies

import com.example.newsapp.news.module.NewsRemoteDataStore
import com.example.newsapp.news.module.NewsRemoteDataStoreImpl
import com.example.newsapp.news.module.DataRepository
import com.example.newsapp.news.module.DataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsModule {
    @Binds
    abstract fun provideNewsRemoteDataStore(newsRemoteDataStore: NewsRemoteDataStoreImpl): NewsRemoteDataStore
    @Binds
    abstract fun provideNewsRepository(newsRepository: DataRepositoryImpl): DataRepository
}

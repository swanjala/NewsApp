package com.example.newsapp.dependencies

import com.example.newsapp.news.module.NewsRemoteDataStore
import com.example.newsapp.news.module.NewsRemoteDataStoreImpl
import com.example.newsapp.news.module.NewsRepository
import com.example.newsapp.news.module.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsModule {
    @Binds
    abstract fun provideNewsRemoteDataStore(newsRemoteDataStore: NewsRemoteDataStoreImpl): NewsRemoteDataStore
    @Binds
    abstract fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}

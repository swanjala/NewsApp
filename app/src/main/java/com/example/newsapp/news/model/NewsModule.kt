package com.example.newsapp.news.model

import dagger.Binds
import dagger.Module

@Module
abstract class NewsModule {
    @Binds
    abstract fun provideNewsRemoteDataStore(newsRemoteDataStore: NewsRemoteDataStoreImpl): NewsRemoteDataStore
    @Binds
    abstract fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}
package com.example.newsapp.dependencies

import com.example.newsapp.data.datastore.NewsLocalDataStore
import com.example.newsapp.data.datastore.NewsLocalDataStoreImpl
import com.example.newsapp.data.datastore.NewsRemoteDataStore
import com.example.newsapp.data.datastore.NewsRemoteDataStoreImpl
import com.example.newsapp.data.datastore.DataRepository
import com.example.newsapp.data.datastore.DataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataStoreModule {
    @Binds
    abstract fun provideNewsLocalDataStore(newsLocalDataStore: NewsLocalDataStoreImpl): NewsLocalDataStore

    @Binds
    abstract fun provideNewsRemoteDataStore(newsRemoteDataStore: NewsRemoteDataStoreImpl): NewsRemoteDataStore

    @Binds
    abstract fun provideNewsRepository(newsRepository: DataRepositoryImpl): DataRepository
}

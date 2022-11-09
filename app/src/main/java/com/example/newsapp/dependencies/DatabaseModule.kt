package com.example.newsapp.dependencies

import android.app.Application
import com.example.newsapp.data.database.ArticleDao
import com.example.newsapp.data.database.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesNewsDatabase(application: Application) = NewsDatabase.getDatabase(application)

    @Provides
    @Singleton
    fun provideArticleDao(newsDatabase: NewsDatabase): ArticleDao {
        return  newsDatabase.articlesDao()
    }

}

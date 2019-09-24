package com.example.newsapp.dependencies

import android.app.Application
import android.arch.persistence.room.Room
import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.datamodels.Sources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule{
    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application):AppDatabase{
      return Room.databaseBuilder(application, AppDatabase::class.java,"newsDb")
              .fallbackToDestructiveMigration()
              .build()
    }
    @Singleton
    @Provides
    fun providesArticlesAccessObject(appDatabase:AppDatabase): ArticleAccessObject{
        return appDatabase.articleAccessObject()

    }
    @Singleton
    @Provides
    fun providesSourcesAccessObject(appDatabase: AppDatabase):SourcesAccessObject{
        return appDatabase.sourcesAccessObject()
    }
}
package com.example.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.SourceTypeConverter

@Database(entities = [Article::class], version = 2, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
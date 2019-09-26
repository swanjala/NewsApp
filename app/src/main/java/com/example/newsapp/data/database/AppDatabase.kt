package com.example.newsapp.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.database.utils.NewsAppTypeConverter
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.Sources


@Database(entities = [Articles::class, Sources::class], version = 17)
@TypeConverters(NewsAppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleAccessObject(): ArticleAccessObject
    abstract fun sourcesAccessObject(): SourcesAccessObject

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        lateinit var dbContext: Context
        fun getDatabase(context: Context): AppDatabase? {

            dbContext = context

            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, "app_database")
                                .addCallback(newsSourcesCallBack)
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build()
                    }

                }
            }

            return INSTANCE
        }


        private val newsSourcesCallBack = object : RoomDatabase.Callback() {
            override fun onOpen(database: SupportSQLiteDatabase) {
                super.onOpen(database)
                DatabaseDataManager(dbContext, INSTANCE!!).execute()
            }
        }

        fun runUpdateTask(context: Context): AppDatabase? {
            dbContext = context

            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, "app_database")
                                .addCallback(newsSourcesCallBack)
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build()
                    }

                }
            }

            return INSTANCE
        }
    }
}

package com.example.newsapp.data.DataRepository

import com.example.newsapp.data.database.AppDatabase

interface MainDataRepository {
    fun setDb(appDatabase: AppDatabase)
}
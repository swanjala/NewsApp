package com.example.newsapp.database

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry

import org.junit.After
import org.junit.Before

import com.example.newsapp.data.database.AppDatabase

open class DatabaseTest {

    protected lateinit var appDatabase: AppDatabase

    @Before
    fun init() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java
        ).build()
    }

    @After
    fun close() {
        appDatabase.close()
    }
}

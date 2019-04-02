package com.example.newsapp.database;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;

import data.database.AppDatabase;

public class DatabaseTest {

    protected AppDatabase appDatabase;

    @Before
    public void init() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class
        ).build();
    }

    @After
    public void close(){
        appDatabase.close();
    }
}

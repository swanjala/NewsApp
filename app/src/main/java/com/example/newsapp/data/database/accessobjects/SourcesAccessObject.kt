package com.example.newsapp.data.database.accessobjects


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import com.example.newsapp.data.datamodels.Sources

@Dao
abstract class SourcesAccessObject {

    @Query("SELECT * FROM Sources")
    abstract fun fetchAllData(): LiveData<List<Sources>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createSourceDataIfNotExists(vararg sourceData: Sources)

    @Query("SELECT DISTINCT(country) FROM Sources ")
    abstract fun fetchCountryLists(): LiveData<List<String>>

    @Query("SELECT DISTINCT(category) FROM Sources")
    abstract fun fetchCategoryList(): LiveData<List<String>>

    @Query("SELECT * FROM Sources WHERE category LIKE :categoryQuery")
    abstract fun fetchNewsNamesByCategory(categoryQuery: String): LiveData<List<Sources>>


}

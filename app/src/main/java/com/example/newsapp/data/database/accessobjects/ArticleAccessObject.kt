package com.example.newsapp.data.database.accessobjects

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import com.example.newsapp.data.datamodels.Articles


@Dao
abstract class ArticleAccessObject {

    @Query("SELECT * FROM articles")
    abstract fun fetchAllData(): LiveData<List<Articles>>

    @Query("SELECT * FROM articles where title LIKE :dataQuery")
    abstract fun fetchAllData(dataQuery: String): LiveData<List<Articles>>

    @Query("SELECT * FROM articles where author LIKE :countryQuery")
    abstract fun fetchAllCountryData(countryQuery: String): LiveData<List<Articles>>

    @Query("SELECT * FROM  articles where url LIKE :domainQuery")
    abstract fun fetchDataByDomain(domainQuery: String): LiveData<List<Articles>>

    @Query("SELECT * FROM articles where toRead = 1")
    abstract fun fetchDataByToRead(): LiveData<List<Articles>>

    @Query("SELECT * FROM articles where favorite = 1")
    abstract fun fetchDataByFavorite(): LiveData<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createDataIfNotExists(vararg articleData: Articles)

    @Query("UPDATE articles SET favorite=:favValue WHERE title LIKE :titleQuery")
    abstract fun setNewsItemToFavorite(favValue: Boolean, titleQuery: String)

    @Query("UPDATE articles SET toRead=:readValue where title LIKE :titleQuery")
    abstract fun setNewsItemToRead(readValue: Boolean, titleQuery: String)

}

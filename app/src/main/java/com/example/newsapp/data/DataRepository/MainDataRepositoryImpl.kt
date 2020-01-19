package com.example.newsapp.data.DataRepository

import android.arch.lifecycle.LiveData
import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.datamodels.Articles
import javax.inject.Inject


class MainDataRepositoryImpl @Inject constructor() {

    @Inject
    lateinit var articlesDataAccessObject: ArticleAccessObject

    @Inject
    lateinit var sourcesAccessObject: SourcesAccessObject


    fun getFetchNewsSource(): LiveData<List<String>> {
        return sourcesAccessObject.fetchCategoryList()
    }

    fun fetchAllArticles(): LiveData<List<Articles>> {
        return articlesDataAccessObject.fetchAllCountryData("Norway")
    }

    fun fetchCountries(): LiveData<List<String>> {
        return sourcesAccessObject.fetchCountryLists()
    }

}
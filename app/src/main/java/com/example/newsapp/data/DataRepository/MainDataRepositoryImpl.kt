package com.example.newsapp.data.DataRepository

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import com.example.newsapp.data.DataRepository.repositorymodel.Source
import com.example.newsapp.data.DataRepository.utils.AppExecutor
import com.example.newsapp.data.api.NetworkService
import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.Sources
import com.example.newsapp.data.DataRepository.utils.Resource
import com.example.newsapp.data.DataRepository.utils.ResourceHandler
import com.example.newsapp.data.api.ApiResponse
import com.example.newsapp.data.api.SourcesResponse
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
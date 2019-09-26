package com.example.newsapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context

import com.example.newsapp.data.DataRepository.ArticleRepository
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.Sources


class NewsViewModel(application: Application) : AndroidViewModel(application) {

    internal var mRepository: ArticleRepository

    private val articlesLiveData: LiveData<List<Articles>>?
    private val articlesNoQueryLiveData: LiveData<List<Articles>>?
    private val sourcesLiveData: LiveData<List<Sources>>?
    private var countryLiveData: LiveData<List<String>>? = null
    private val newsCategories: LiveData<List<String>>?
    private var newsByFavorite: LiveData<List<Articles>>? = null
    private var newsBySetToRead: LiveData<List<Articles>>? = null
    private var articlesByDomain: LiveData<List<Articles>>? = null
    private var articlesByTitle: LiveData<List<Articles>>? = null
    private var articlesByCountry: LiveData<List<Articles>>? = null
    private var sourcesByNewsCategory: LiveData<List<Sources>>? = null
    private val context: Context

    init {
        mRepository = ArticleRepository(application)
        sourcesLiveData = mRepository.sources
        articlesNoQueryLiveData = mRepository.allArticlesNoQuery

        articlesLiveData = mRepository.getmAllArticles()
        countryLiveData = mRepository.countries
        newsCategories = mRepository.newsCategories
        countryLiveData = mRepository.countries
        this.context = application.applicationContext
    }

    fun fetchAllArticlesNoQuery(): LiveData<List<Articles>>? {
        return articlesNoQueryLiveData
    }

    fun fetchArticlesByTitle(queryParam: String): LiveData<List<Articles>> {

        articlesByTitle = mRepository.getArticlesByTitle(context, queryParam)

        return articlesByTitle
    }

    fun fetchAllSources(): LiveData<List<Sources>>? {
        return sourcesLiveData
    }

    fun fetchAllCountries(): LiveData<List<String>>? {
        return countryLiveData
    }

    fun fetchArticlesByCountry(country: String): LiveData<List<Articles>> {

        articlesByCountry = mRepository.getArticlesByCountry(context, country)

        return articlesByCountry
    }

    fun fetchNewsCategories(): LiveData<List<String>>? {
        return newsCategories
    }

    fun fetchArticlesByDomain(query: String): LiveData<List<Articles>> {

        articlesByDomain = mRepository.getArticlesByDomain(context, query)
        return articlesByDomain
    }

    fun fetchDataByNewsCategories(categoryQuery: String): LiveData<List<Sources>> {
        sourcesByNewsCategory = mRepository.getSourcesByNewsCategory(categoryQuery)

        return sourcesByNewsCategory
    }

    fun fetchDataByFavorite(): LiveData<List<Articles>> {

        newsByFavorite = mRepository.dataByFavorite
        return newsByFavorite
    }

    fun fetchDataBySetToRead(): LiveData<List<Articles>> {
        newsBySetToRead = mRepository.dataBySetToRead
        return newsBySetToRead
    }

    fun setNewsItemsByFavorite(setItem: Boolean, query: String) {

        mRepository.insertFavorite(setItem, query)
    }

    fun setNewsItemsBySetToRead(setItem: Boolean, query: String) {
        mRepository.insertSetToRead(setItem, query)
    }

}

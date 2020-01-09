package com.example.newsapp.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context

import com.example.newsapp.data.DataRepository.ArticleRepository
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.Sources
import javax.inject.Inject


class NewsViewModel(application: Application) : ViewModel() {

    internal var mRepository: ArticleRepository

    private val articlesLiveData: LiveData<List<Articles>>?
    private val articlesNoQueryLiveData: LiveData<List<Articles>>?
    private val sourcesLiveData: LiveData<List<Sources>>?
    private var countryLiveData: LiveData<List<String>>? = null
    private val newsCategories: LiveData<List<String>>?
    private var newsByFavorite: LiveData<List<Articles>>? = null
    private var newsBySetToRead: LiveData<List<Articles>>? = null
    private var articlesByDomain: LiveData<List<Articles>>? = null
    private var articlesByCountry: LiveData<List<Articles>>? = null
    private var sourcesByNewsCategory: LiveData<List<Sources>>? = null

    @SuppressLint("StaticFieldLeak")
    @Inject
    lateinit var context: Context

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

        return mRepository.getArticlesByTitle(context, queryParam)

    }

    fun fetchAllSources(): LiveData<List<Sources>>? {
        return sourcesLiveData
    }

    fun fetchAllCountries(): LiveData<List<String>>? {
        return countryLiveData
    }

    fun fetchArticlesByCountry(country: String): LiveData<List<Articles>> {

        return mRepository.getArticlesByCountry(context, country)

    }

    fun fetchNewsCategories(): LiveData<List<String>>? {
        return newsCategories
    }

    fun fetchArticlesByDomain(query: String): LiveData<List<Articles>> {

        return mRepository.getArticlesByDomain(context, query)
    }

    fun fetchDataByNewsCategories(categoryQuery: String): LiveData<List<Sources>> {

        return  mRepository.getSourcesByNewsCategory(categoryQuery)
    }

    fun fetchDataByFavorite(): LiveData<List<Articles>> {

        return mRepository.dataByFavorite

    }

    fun fetchDataBySetToRead(): LiveData<List<Articles>> {
        return mRepository.dataBySetToRead
    }

    fun setNewsItemsByFavorite(setItem: Boolean, query: String) {

        return mRepository.insertFavorite(setItem, query)
    }

    fun setNewsItemsBySetToRead(setItem: Boolean, query: String) {
        return mRepository.insertSetToRead(setItem, query)
    }

}

package com.example.newsapp.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.newsapp.data.DataRepository.ArticleRepository
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.Sources
import javax.inject.Inject
import javax.xml.transform.Source


class NewsViewModel(application: Application) : ViewModel() {

    internal var mRepository: ArticleRepository

    private val _currentChartPagerItem = MutableLiveData<Int>().apply { value = 0 }
    val currentChartPagerItem: LiveData<Int>
        get() = _currentChartPagerItem


    private val _articleLiveData = MutableLiveData<List<Articles>>()
    val articlesLiveData: LiveData<List<Articles>>?
        get() = _articleLiveData

    private val _articlesNoQueryLiveData = MutableLiveData<List<Articles>>()
    val articlesNoQueryLiveData: LiveData<List<Articles>>
        get() = _articlesNoQueryLiveData


    private val _sourcesLiveData = MutableLiveData<List<Sources>>()
    val sourcesLiveData: LiveData<List<Source>>
        get() = _sourcesLiveData

    private val _countryLiveData = MutableLiveData<List<String>>()
    val countryLiveData: LiveData<List<String>>
        get() = _countryLiveData

    private val _newCategories = MutableLiveData<List<String>>()
    val newCategories: LiveData<List<String>>
        get() = _newCategories

    private val _newsByFavorite = MutableLiveData<List<Articles>>()
    val newsByFavorite: LiveData<List<Articles>>
        get() = _newsByFavorite

    private val _newsBySetToRead = MutableLiveData<List<Articles>>()
    val newsBySetToRead: LiveData<List<Articles>>
        get() = _newsBySetToRead

    private val _articlesByDomain = MutableLiveData<List<Articles>>()
    val articlesByDomain: LiveData<List<Articles>>
        get() = _articlesByDomain

    private var _articlesByCountry = MutableLiveData<List<Articles>>()
    val articlesByCountry: LiveData<List<Articles>>
        get() = _articlesByCountry

    private var _sourcesByNewsCategory = MutableLiveData<List<Sources>>()
        val sourcesByNewsCategory:LiveData<List<Sources>>
    get() = _sourcesByNewsCategory

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

        return mRepository.getSourcesByNewsCategory(categoryQuery)
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

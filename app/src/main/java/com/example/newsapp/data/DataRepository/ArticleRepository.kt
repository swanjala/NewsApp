package com.example.newsapp.data.DataRepository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import com.example.newsapp.data.api.NetworkService
import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.Sources
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject

class ArticleRepository(private val application: Application) {

    private var articleAccessObject: ArticleAccessObject? = null
    private var sourcesAccessObject: SourcesAccessObject? = null
    private val mAllArticles: LiveData<List<Articles>>? = null
    private var mAllArticlesByTitle: LiveData<List<Articles>>? = null
    private var mAllArticlesByDomain: LiveData<List<Articles>>? = null
    var allArticlesNoQuery: LiveData<List<Articles>>? = null
        private set
    var sources: LiveData<List<Sources>>? = null
        private set
    private var mArticlesByCountry: LiveData<List<Articles>>? = null
    var countries: LiveData<List<String>>? = null
        private set
    var newsCategories: LiveData<List<String>>? = null
        private set
    private var mNewsBySetToRead: LiveData<List<Articles>>? = null
    private var mNewsByFavorite: LiveData<List<Articles>>? = null
    private val mNewsByDomain: LiveData<List<Articles>>? = null

    private var mSourcesByNewsCategory: LiveData<List<Sources>>? = null

    private val mTopHeadlines: List<Articles>? = null
    private val mTopHeadlinesByCountryCategory: List<Articles>? = null
    private val mTopHeadlinesByCountry: List<Articles>? = null
    private val mTopHeadlinesBySearch: List<Articles>? = null

    val dataByFavorite: LiveData<List<Articles>>
        get() {
            mNewsByFavorite = articleAccessObject!!.fetchDataByFavorite()
            return mNewsByFavorite!!
        }
    val dataBySetToRead: LiveData<List<Articles>>
        get() {
            mNewsBySetToRead = articleAccessObject!!.fetchDataByToRead()
            return mNewsBySetToRead!!
        }


    init {
        initializeDatabaseValues(application)

    }

    fun initializeDatabaseValues(application: Application) {
        val appDatabase = AppDatabase.getDatabase(application)
        articleAccessObject = appDatabase?.articleAccessObject()
        sourcesAccessObject = appDatabase?.sourcesAccessObject()
        sources = sourcesAccessObject!!.fetchAllData()
        countries = sourcesAccessObject!!.fetchCountryLists()
        allArticlesNoQuery = articleAccessObject!!.fetchAllData()
        newsCategories = sourcesAccessObject!!.fetchCategoryList()

    }

    fun initializeDatabaseValues(application: Application, query: String) {

        val database = AppDatabase.getDatabase(application)
        articleAccessObject = database?.articleAccessObject()
        sourcesAccessObject = database?.sourcesAccessObject()
    }

    fun getmAllArticles(): LiveData<List<Articles>>? {

        return mAllArticles
    }

    fun getSourcesByNewsCategory(categoryQuery: String): LiveData<List<Sources>> {

        mSourcesByNewsCategory = sourcesAccessObject!!.fetchNewsNamesByCategory(categoryQuery)
        return mSourcesByNewsCategory!!
    }

    fun getArticlesByTitle(context: Context, title: String): LiveData<List<Articles>> {

        return mAllArticlesByTitle!!

    }

    fun getArticlesByDomain(context: Context, domain: String): LiveData<List<Articles>> {
        val domain = domain
        mAllArticlesByDomain = articleAccessObject!!.fetchDataByDomain("%$domain%")

        return mAllArticlesByDomain!!

    }

    fun insert(articles: List<Articles>) {
        insertAsyncTask(articleAccessObject!!).execute(articles)

    }

    fun insertFavorite(setToFavorite: Boolean, title: String) {

        val hashMap = HashMap<String, Any>()
        hashMap.put("favorite", setToFavorite)
        hashMap.put("title", title)
    }

    fun insertSetToRead(setToReadValue: Boolean, title: String) {
        val hashMap = HashMap<String, Any>()
        hashMap.put("toReadValue", setToReadValue)
        hashMap.put("title", title)
    }

    fun getArticlesByCountry(context: Context, country: String): LiveData<List<Articles>> {

        return mArticlesByCountry!!
    }

    class insertAsyncTask internal constructor(private val mAsyckTaskAccessObject: ArticleAccessObject) : AsyncTask<List<Articles>, Void, Void>() {

        override fun doInBackground(vararg params: List<Articles>): Void? {

            for (index in 0 until params[0].size) {
                mAsyckTaskAccessObject.createDataIfNotExists(params[0][index])
            }

            return null
        }

    }

    class insertDataFromCountry
    @Inject internal constructor(private val context: Context,
                                 private val queryValue: String,
                                 networkService: NetworkService,
                                 private val mInsertByCountryAccessObject: ArticleAccessObject)
        : AsyncTask<String, Void, Void>() {
        private var articlesByCountry: List<Articles>? = null
        private val modifiedArticleList: List<Articles>? = null

        override fun doInBackground(vararg strings: String): Void? {
            if (strings != null) {

                val connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = connectivityManager.activeNetworkInfo

                val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

                return null

            }


            class insertDataFromTitle
            @Inject
            internal constructor(private val context: Context,
                                 private val queryValue: String,
                                 networkService: NetworkService,
                                 private val mInsertByTitleAccessObject: ArticleAccessObject) : AsyncTask<String, Void, Void>() {
                private var articlesByTitle: List<Articles>? = null

                override fun doInBackground(vararg strings: String): Void? {

                    return null
                }

            }

            class insertDataFromDomain
            @Inject
            internal constructor(private val context: Context,
                                 domain: String,
                                 networkService: NetworkService,
                                 private val mInsertByTitleAccessObject: ArticleAccessObject) : AsyncTask<String, Void, Void>() {

                private var queryValue: String? = null
                private var articlesByDomain: List<Articles>? = null

                init {
                    this.queryValue = domain

                    val pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?")
                    val matcher = pattern.matcher(domain)
                    matcher.find()
                    this.queryValue = matcher.group(2).substring(4)
                }

                override fun doInBackground(vararg strings: String): Void? {
                    if (strings != null) {

                        val connectivityManager = context
                            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                        val activeNetwork = connectivityManager.activeNetworkInfo

                        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

                    }
                    return null
                }
            }
            return null
        }
    }
}


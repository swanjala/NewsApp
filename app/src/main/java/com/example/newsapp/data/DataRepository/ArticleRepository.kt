package com.example.newsapp.data.DataRepository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.newsapp.data.api.NetworkService

import java.util.HashMap
import java.util.regex.Matcher
import java.util.regex.Pattern

import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.datamodels.Articles
import com.example.newsapp.data.datamodels.CountryConstants
import com.example.newsapp.data.datamodels.DataResponse
import com.example.newsapp.data.datamodels.Sources
import com.example.newsapp.dependencies.AppModule_ProvideNetworkServiceFactory
import com.example.newsapp.dependencies.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.PipedReader
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

        try {
            insertDataFromTitle(context, title, articleAccessObject).execute()

        } finally {

            mAllArticlesByTitle = articleAccessObject!!.fetchAllData("%$title%")
        }

        return mAllArticlesByTitle!!

    }

    fun getArticlesByDomain(context: Context, domain: String): LiveData<List<Articles>> {
        var domain = domain

        try {
            insertDataFromDomain(context, domain, articleAccessObject!!).execute()

        } finally {

            val pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?")
            val matcher = pattern.matcher(domain)
            matcher.find()
            domain = matcher.group(2).substring(4)

            mAllArticlesByDomain = articleAccessObject!!
                    .fetchDataByDomain("%$domain%")
        }

        return mAllArticlesByDomain!!

    }

    fun insert(articles: List<Articles>) {
        insertAsyncTask(articleAccessObject!!).execute(articles)

    }

    fun insertFavorite(setToFavorite: Boolean, title: String) {

        val hashMap = HashMap<String,Any>()
        hashMap.put("favorite", setToFavorite)
        hashMap.put("title", title)

        insertFavoriteArticle(articleAccessObject!!).execute(hashMap)

    }

    fun insertSetToRead(setToReadValue: Boolean, title: String) {
        val hashMap = HashMap<String,Any>()
        hashMap.put("toReadValue", setToReadValue)
        hashMap.put("title", title)


        insertSetToRead(articleAccessObject!!).execute(hashMap)

    }

    fun getArticlesByCountry(context: Context, country: String): LiveData<List<Articles>> {

        try {

            val countryConstants = CountryConstants()

            val countryMap = countryConstants.countryListData()

            for (key in countryMap.keys) {
                if (country == countryMap[key]) {

                    insertDataFromCountry(context, key.toLowerCase(), articleAccessObject).execute()
                }
            }

        } finally {

            mArticlesByCountry = articleAccessObject!!
                    .fetchAllCountryData("%$country%")
        }

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

                //val apiManager = ApiManager(context, queryValue)

                val connectivityManager = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = connectivityManager.activeNetworkInfo

                val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

                if (isConnected) {

                    val call = networkService.getHeadlines()
                    call.enqueue(object : Callback<DataResponse> {
                        override fun onResponse(call: Call<DataResponse>,
                                                response: Response<DataResponse>) {

                            if (response.body() != null) {
                                val countryConstants = CountryConstants()
                                val countryQuery = countryConstants
                                        .countryListData()[queryValue.toUpperCase()]

                                articlesByCountry = response.body()!!.articles
                                for (index in articlesByCountry!!.indices) {

                                    val author = articlesByCountry!![index].author
                                    articlesByCountry!![index]
                                            .author = author + "\n" + countryQuery

                                    mInsertByCountryAccessObject
                                            .createDataIfNotExists(articlesByCountry!![index])

                                }
                            }
                        }

                        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                            Log.d("Data", t.localizedMessage)

                        }
                    })
                } else {

                    val handler = Handler(context.mainLooper)
                    handler.post {
                        Toast.makeText(context, "You are not connected to the internet",
                                Toast.LENGTH_LONG).show()
                    }
                }
            }
            return null
        }

    }


    class insertDataFromTitle
    @Inject
    internal constructor(private val context: Context,
                         private val queryValue: String,
                         networkService: NetworkService,
                         private val mInsertByTitleAccessObject: ArticleAccessObject) : AsyncTask<String, Void, Void>() {
        private var articlesByTitle: List<Articles>? = null

        override fun doInBackground(vararg strings: String): Void? {
            if (strings != null) {


                val connectivityManager = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = connectivityManager.activeNetworkInfo

                val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

                if (isConnected) {

                    val call = networkService.getArticles()
                    call.enqueue(object : Callback<DataResponse> {
                        override fun onResponse(call: Call<DataResponse>,
                                                response: Response<DataResponse>) {

                            if (response.body() != null) {

                                articlesByTitle = response.body()!!.articles

                                for (index in articlesByTitle!!.indices) {

                                    mInsertByTitleAccessObject.createDataIfNotExists(articlesByTitle!![index])

                                }
                            }
                        }

                        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                            Log.d("Data", t.localizedMessage)

                        }
                    })
                } else {

                    val handler = Handler(context.mainLooper)
                    handler.post {
                        Toast.makeText(context, "You are not connected to the internet",
                                Toast.LENGTH_LONG).show()
                    }
                }
            }
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

                if (isConnected) {

                    val call = networkService.getNewsByDomains()
                    call.enqueue(object : Callback<DataResponse> {
                        override fun onResponse(call: Call<DataResponse>,
                                                response: Response<DataResponse>) {

                            if (response.body() != null) {

                                articlesByDomain = response.body()!!.articles

                                for (index in articlesByDomain!!.indices) {

                                    mInsertByTitleAccessObject.createDataIfNotExists(articlesByDomain!![index])

                                }
                            }
                        }

                        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                            Log.d("Data", t.localizedMessage)

                        }
                    })
                } else {

                    val handler = Handler(context.mainLooper)
                    handler.post {
                        Toast.makeText(context, "You are not connected to the internet",
                                Toast.LENGTH_LONG).show()
                    }
                }
            }
            return null
        }

    }

    class insertFavoriteArticle
    internal constructor(private val mFavoriteAsyncTaskAccessObject: ArticleAccessObject) : AsyncTask<HashMap<*, *>, Void, Void>() {
        override fun doInBackground(vararg hashMaps: HashMap<*, *>): Void? {
            if (hashMaps != null) {

                val favorite = hashMaps[0]["favorite"] as Boolean
                val title = hashMaps[0]["title"] as String?
                mFavoriteAsyncTaskAccessObject.setNewsItemToFavorite(favorite,
                        title!!)
            }
            return null
        }

    }

    class insertSetToRead internal constructor(private val mSetToReadAsyncTaskAccessObject: ArticleAccessObject) : AsyncTask<HashMap<*, *>, Void, Void>() {
        override fun doInBackground(vararg hashMaps: HashMap<*, *>): Void? {
            if (hashMaps != null) {

                val toReadValue = hashMaps[0]["toReadValue"] as Boolean
                val title = hashMaps[0]["title"] as String?
                mSetToReadAsyncTaskAccessObject.setNewsItemToRead(toReadValue, title!!)
            }
            return null
        }
    }

}

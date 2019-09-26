package com.example.newsapp.data.database

import android.content.Context
import android.os.AsyncTask
import android.util.Log

import com.example.newsapp.R

import com.example.newsapp.data.database.accessobjects.ArticleAccessObject
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject
import com.example.newsapp.data.datamodels.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DatabaseDataManager : AsyncTask<Void, Void, Void> {

    private val articleAccessObject: ArticleAccessObject
    private var sourcesAccessObject: SourcesAccessObject? = null

    private var apiManager: ApiManager? = null

    private var context: Context? = null
    private val query: String?

    constructor(context: Context, appDatabase: AppDatabase, query: String) {
        articleAccessObject = appDatabase.articleAccessObject()
        sourcesAccessObject = appDatabase.sourcesAccessObject()
        this.context = context
        this.query = query
    }

    constructor(context: Context, appDatabase: AppDatabase) {
        this.context = context
        sourcesAccessObject = appDatabase.sourcesAccessObject()
    }

    override fun doInBackground(vararg params: Void): Void? {
        if (query == null) {
            apiManager = ApiManager(context)

            val sourceCall = apiManager!!.getSources()
            sourceCall.enqueue(object : Callback<DataResponse> {
                override fun onResponse(call: Call<DataResponse>, sourceResponse: Response<DataResponse>) {

                    if (sourceResponse.body() != null) {
                        for (index in 0 until sourceResponse.body()!!.sources!!.size) {
                            sourcesAccessObject!!.createSourceDataIfNotExists(sourceResponse.body()!!
                                    .sources!![index])
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d(context!!.getString(R.string.database_manager_sources_error),
                            t.localizedMessage)
                }
            })


        } else {

            Log.d("datbase manafer", query)

            apiManager = ApiManager(context, query)
            val call = apiManager!!.getArticles()
            call.enqueue(object : Callback<DataResponse> {
                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {


                    if (response.body() != null) {

                        /* Consider removing loop*/

                        Log.d("Data gotten:", response.body().toString())

                        for (i in 0 until response.body()!!.articles!!.size) {

                            articleAccessObject.createDataIfNotExists(response.body()!!.articles!![i])
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d("Data", t.localizedMessage)

                }
            })
        }
        return null
    }
}

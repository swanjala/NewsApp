package com.example.newsapp.data.DataRepository

import android.app.Application
import android.arch.lifecycle.LiveData
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
import javax.inject.Singleton
/*TODO
  *  - Implement thread executors*/
@Singleton
class MainDataRepositoryImpl @Inject constructor(networkService: NetworkService,
                                                 appDatabase: AppDatabase,
                                                 articlesAccessObject: ArticleAccessObject,
                                                 sourcesAccessObject: SourcesAccessObject
                                                 appExecutor: AppExecutor):MainDataRepository{
    private var mAppExecutors = appExecutor
    private var mNetworkService = networkService

    @Inject
    private var articlesDataAccessObject = articlesAccessObject.fetchAllData()


    private lateinit var sourcesAccessObject: SourcesAccessObject

    private lateinit var mCountries : LiveData<List<String>>

    private lateinit var mAllArticles : LiveData<List<Articles>>
    private lateinit var mArticlesByTitle : LiveData<List<Articles>>
    private lateinit var mArticlesByDomain : LiveData<List<Articles>>
    private lateinit var mArticlesNoQueryParams : LiveData<List<Articles>>
    private lateinit var mArticlesByCountry : LiveData<List<Articles>>
    private lateinit var mNewsBySetToRead : LiveData<List<Articles>>
    private lateinit var mNewsByFavorite : LiveData<List<Articles>>
    private lateinit var mNewsByDomain : LiveData<List<Articles>>

    private lateinit var mSources : LiveData<List<Sources>>
    private lateinit var mSourceByNewsCategory: LiveData<List<Sources>>


    fun getFetchNewsSource():LiveData<Resource<List<Sources>>>{

    }

    val allSources: LiveData<Resource<List<Sources>>>
        get() = object :ResourceHandler<List<Sources>, SourcesResponse>(mAppExecutors){

            override fun saveCallResult(item:SourcesResponse){
                sourcesAccessObject.createSourceDataIfNotExists(item.results)
            }
            override fun shouldFetch(data:List<Sources>?): Boolean{
                return data == null || data.isEmpty()
            }
            override fun loadFromDb():LiveData<List<Sources>>{
                return sourcesAccessObject.fetchCategoryList()
            }
            override fun createCall():LiveData<ApiResponse<SourcesResponse>>{
                return mNetworkService.getSources("")
            }
    }.asLiveData()





}
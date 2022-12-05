package com.example.newsapp.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.news.module.DataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _response = MutableLiveData<News>()
    val response: LiveData<News>
        get() = _response

    private val _onSave = MutableLiveData<Boolean>(false)
    val onSave: LiveData<Boolean>
        get() = _onSave

    private val _savedArticles = MutableLiveData<List<Article>>()
    val savedArticles: LiveData<List<Article>>
        get() = _savedArticles

    init {
        viewModelScope.launch {
            getNewsInfo()
        }
    }

    private suspend fun getNewsInfo() {
        val newsValues = dataRepository.getNewsData()
        newsValues?.let {
            _response.postValue(
                it
            )
        }
    }

    suspend fun getSavedArticles() {
        val articles = dataRepository.getSavedArticles()
        articles.collect {
            _savedArticles.postValue(it)
        }
    }

    suspend fun saveNewsArticle(article: Article) {
        dataRepository.insertNewArticle(article)
    }
}

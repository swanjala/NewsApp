package com.example.newsapp.news.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.network.model.News
import com.example.newsapp.news.module.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _response = MutableLiveData<News>()
    val response: LiveData<News>
        get() = _response

    private val _onSave = MutableLiveData<Boolean>(false)
    val onSave: LiveData<Boolean>
    get() = _onSave

    init {
        viewModelScope.launch {
            getNewsInfo()
        }
    }

    private suspend fun getNewsInfo() {
        val newsValues = newsRepository.getNewsData()
        _response.postValue(
            newsValues
        )
    }

    fun saveNewsArticle() {
        _onSave.postValue(true)
    }
}

package com.example.newsapp.news

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.datastore.DataRepository
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.data.model.NewsCategory.BUSINESS
import com.example.newsapp.data.model.NewsCategory.ENTERTAINMENT
import com.example.newsapp.data.model.NewsCategory.GENERAL
import com.example.newsapp.data.model.NewsCategory.KEYWORD
import com.example.newsapp.data.model.NewsCategory.SCIENCE
import com.example.newsapp.data.model.NewsCategory.SPORTS
import com.example.newsapp.data.model.NewsCategory.TECHNOLOGY
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _response = MutableLiveData<News?>()
    val response: LiveData<News?>
        get() = _response

    private val _savedArticles = MutableLiveData<List<Article>>()
    val savedArticles: LiveData<List<Article>>
        get() = _savedArticles

    fun updateData(requestCategory: NewsCategory) = viewModelScope.launch {
        when (requestCategory) {
            BUSINESS -> {
                getNewsCategory(BUSINESS)
            }
            ENTERTAINMENT -> {
                getNewsCategory(ENTERTAINMENT)
            }
            GENERAL -> {
                getNewsCategory(GENERAL)
            }
            TECHNOLOGY -> {
                getNewsCategory(TECHNOLOGY)
            }
            SCIENCE -> {
                getNewsCategory(SCIENCE)
            }
            SPORTS -> {
                getNewsCategory(SPORTS)
            }
            else -> {
                getNewsCategory(KEYWORD)
            }
        }
    }

    private suspend fun getNewsCategory(category: NewsCategory) {
        val categoryNewsValues = dataRepository.getNewsByCategory(
            category = category.toString().toLowerCase(
                Locale.current
            )
        )
        categoryNewsValues?.let {
            _response.postValue(it)
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

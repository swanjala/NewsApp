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
                getNewsCategory(BUSINESS, dynamicPageNumber = 0)
            }
            ENTERTAINMENT -> {
                getNewsCategory(ENTERTAINMENT, dynamicPageNumber = 0)
            }
            GENERAL -> {
                getNewsCategory(GENERAL, dynamicPageNumber = 0)
            }
            TECHNOLOGY -> {
                getNewsCategory(TECHNOLOGY, dynamicPageNumber = 0)
            }
            SCIENCE -> {
                getNewsCategory(SCIENCE, dynamicPageNumber = 0)
            }
            SPORTS -> {
                getNewsCategory(SPORTS, dynamicPageNumber = 0)
            }
            else -> {
                getNewsCategory(KEYWORD, dynamicPageNumber = 0)
            }
        }
    }

    private suspend fun getNewsCategory(category: NewsCategory, dynamicPageNumber: Int) {
        val categoryNewsValues = dataRepository.getNewsByCategory(
            category = category.toString().toLowerCase(
                Locale.current
            ),
            pageNumber = dynamicPageNumber
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

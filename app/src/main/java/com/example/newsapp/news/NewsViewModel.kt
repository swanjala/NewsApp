package com.example.newsapp.news

import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.composables.composableModels.NewsUiState
import com.example.newsapp.data.datastore.DataRepository
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.data.model.NewsCategory.BUSINESS
import com.example.newsapp.data.model.NewsCategory.ENTERTAINMENT
import com.example.newsapp.data.model.NewsCategory.GENERAL
import com.example.newsapp.data.model.NewsCategory.KEYWORD
import com.example.newsapp.data.model.NewsCategory.SCIENCE
import com.example.newsapp.data.model.NewsCategory.SPORTS
import com.example.newsapp.data.model.NewsCategory.TECHNOLOGY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private var _newsUiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _newsUiState.asStateFlow()

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

    suspend fun getAllNews() {
        try {
            val newsItems = dataRepository.getAllNews()
            _newsUiState.value = when (newsItems) {
                null -> NewsUiState(errorState = true)
                else -> NewsUiState(
                    newsItems = newsItems.articles,
                    errorState = false
                )
            }
        } catch (
            error: Throwable
        ) {
            NewsUiState(errorState = true)
        }
    }

    fun getSources() = viewModelScope.launch {
        val dataSourceNewsValues = dataRepository.getNewsFromDataSource()
        dataSourceNewsValues?.let { data ->
            with(data) {
                if (status != "ok") {
                    // Implements the error state of the news ui screen.
                    _newsUiState.value = NewsUiState(
                        errorState = true
                    )
                } else {
                    // Implements the ui state for sources state
                    _newsUiState.value = NewsUiState(
                        sources = sources
                    )
                }
            }
        }
    }

    private suspend fun getNewsCategory(category: NewsCategory) {
        try {
            val newsItems = dataRepository.getNewsByCategory(
                category = category.toString().toLowerCase(
                    Locale.current
                )
            )
            newsItems?.let {
                _newsUiState.value = NewsUiState(
                    newsItems = it.articles,
                    errorState = false
                )
            }
        } catch (error: Throwable) {
            Log.getStackTraceString(error)
            _newsUiState.value = NewsUiState(
                errorState = true
            )
        }
    }

    suspend fun getSavedArticles() {
        try {
            dataRepository.getSavedArticles().collect { item ->
                _newsUiState.value = NewsUiState(
                    savedArticles = item,
                    errorState = false
                )
            }
        } catch (error: Throwable) {
            Log.getStackTraceString(error)
            _newsUiState.value = NewsUiState(
                errorState = true
            )
        }
    }

    suspend fun saveNewsArticle(article: Article) {
        dataRepository.insertNewArticle(article)
    }
}


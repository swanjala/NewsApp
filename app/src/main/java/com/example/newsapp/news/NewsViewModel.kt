package com.example.newsapp.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.composables.composableModels.NewsUiState
import com.example.newsapp.data.datastore.DataRepository
import com.example.newsapp.data.model.Article
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

    fun getNewsByCategory(requestType: String) = viewModelScope.launch {
        if (requestType.isEmpty()) {
            getAllNews()
        } else {
            getCategories(requestType)
        }
    }

    private suspend fun getCategories(requestType: String) {
        try {
            val newsItems = dataRepository.getNewsByCategory(
                category = requestType
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

    suspend fun getAllNews() {
        try {
            dataRepository.getAllNews().collect { newsItem ->
                _newsUiState.value = when (newsItem) {
                    null -> NewsUiState(errorState = true)
                    else -> NewsUiState(
                        newsItems = newsItem.articles,
                        errorState = false
                    )
                }
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

    suspend fun getSavedArticles() {
        try {
            dataRepository.getSavedArticles()
                .collect { item ->
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


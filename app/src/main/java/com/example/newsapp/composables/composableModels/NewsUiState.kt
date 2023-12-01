package com.example.newsapp.composables.composableModels

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsSource

data class NewsUiState(
    val newsItems: List<Article>? = null,
    val savedArticles: List<Article>? = null,
    val sources: List<NewsSource>? = null,
    val errorState: Boolean = false
)

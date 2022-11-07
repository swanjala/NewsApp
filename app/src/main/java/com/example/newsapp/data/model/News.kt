package com.example.newsapp.data.model

data class News(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)